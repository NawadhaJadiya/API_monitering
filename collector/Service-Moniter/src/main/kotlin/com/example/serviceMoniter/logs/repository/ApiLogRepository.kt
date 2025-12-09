package com.example.serviceMoniter.logs.repository

import com.example.serviceMoniter.logs.model.ApiLog
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ApiLogRepository : MongoRepository<ApiLog, String> {
    // Add custom query methods here if needed in the future
}
