package com.example.serviceMoniter.logs.repository

import com.example.serviceMoniter.logs.model.RateLimitHit
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RateLimitHitRepository : MongoRepository<RateLimitHit, String> {
    // Add custom query methods here if needed in the future
}
