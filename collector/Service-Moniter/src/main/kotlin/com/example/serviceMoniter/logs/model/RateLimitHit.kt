package com.example.serviceMoniter.logs.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("rate_hit_limit")
data class RateLimitHit(
    @Id
    val id: String? = null,
    val service: String? = null,
    val endpoint: String? = null,
    val timestamp: Instant? = Instant.now(),
    val reason: String = "rate-limit-hit"
)
