package com.example.serviceMoniter.config
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(
    basePackages = ["com.example.serviceMoniter.meta.repository"],  
    mongoTemplateRef = "metaMongoTemplate"              
)
class MetaMongoConfig {

    
    @Value("\${spring.data.mongodb.meta.uri}")
    lateinit var metaUri: String

    
    @Value("\${spring.data.mongodb.meta.database}")
    lateinit var metaDatabase: String

    
    @Bean(name = ["metaMongoClient"])
    @Primary
    fun metaMongoClient(): MongoClient {
        return MongoClients.create(metaUri)
    }

    
    @Bean(name = ["metaMongoDbFactory"])
    @Primary
    fun metaMongoDbFactory(): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(metaMongoClient(), metaDatabase)
    }

   
    @Bean(name = ["metaMongoTemplate"])
    @Primary
    fun metaMongoTemplate(): MongoTemplate {
        return MongoTemplate(metaMongoDbFactory())
    }
}
