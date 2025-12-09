package com.example.serviceMoniter.meta.repository

import com.example.serviceMoniter.meta.model.ResolveStatus
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ResolveStatusRepository : MongoRepository<ResolveStatus, String> {
    // Add custom query methods here if needed
}
