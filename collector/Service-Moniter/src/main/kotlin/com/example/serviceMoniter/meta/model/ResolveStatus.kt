package com.example.serviceMoniter.meta.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("resolve_status")
data class ResolveStatus(
    @Id
    val id: String? = null,
    val userId: String,
    val resolvedLogId: String,
    val timestamp: Instant? = Instant.now()
)
