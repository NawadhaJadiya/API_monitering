package com.example.serviceMoniter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class])
@ComponentScan(basePackages = ["com.example.serviceMoniter"])
class ServiceMoniterApplication

fun main(args: Array<String>) {
	runApplication<ServiceMoniterApplication>(*args)
}
