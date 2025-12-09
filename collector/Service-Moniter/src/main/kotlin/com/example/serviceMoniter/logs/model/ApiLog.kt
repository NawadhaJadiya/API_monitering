package com.example.serviceMoniter.logs.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("api_logs")
data class ApiLog(
    @Id
    val id: String? = null,
    val service: String? = null,
    val endpoint: String? = null,
    val method: String? = null,
    val status: Int? = null,
    val requestSize: Long? = null,
    val responseSize: Long? = null,
    val latencyMs: Long? = null,
    val timestamp: Instant? = Instant.now(),
    val extra: Map<String, Any>? = null
)