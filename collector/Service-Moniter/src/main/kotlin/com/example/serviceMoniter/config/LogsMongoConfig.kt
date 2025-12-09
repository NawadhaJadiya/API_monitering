package com.example.serviceMoniter.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(
    basePackages = ["com.example.serviceMoniter.logs.repository"],   
    mongoTemplateRef = "logsMongoTemplate"               
)
class LogsMongoConfig {

    
    @Value("\${spring.data.mongodb.logs.uri}")
    lateinit var logsUri: String

   
    @Value("\${spring.data.mongodb.logs.database}")
    lateinit var logsDatabase: String

    
    @Bean(name = ["logsMongoClient"])
    fun logsMongoClient(): MongoClient {
        if (this::logsUri.isInitialized.not() || logsUri.isBlank()) {
            throw IllegalStateException("Missing configuration: 'spring.data.mongodb.logs.uri' is not set or empty. Please set it in application.properties for the logs MongoDB connection.")
        }
        try {
            return MongoClients.create(logsUri)
        } catch (ex: Exception) {
            throw IllegalStateException("Failed to create MongoClient for logs using uri='$logsUri'. Check URI, network/DNS and credentials. Cause: ${ex.message}", ex)
        }
    }

  
    @Bean(name = ["logsMongoDbFactory"])
    fun logsMongoDbFactory(): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(logsMongoClient(), logsDatabase)
    }

    
    @Bean(name = ["logsMongoTemplate"])
    fun logsMongoTemplate(): MongoTemplate {
        return MongoTemplate(logsMongoDbFactory())
    }
}
