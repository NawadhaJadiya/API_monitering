package com.example.serviceMoniter.logs.service

import com.example.serviceMoniter.logs.model.ApiLog
import com.example.serviceMoniter.logs.model.RateLimitHit
import com.example.serviceMoniter.logs.repository.ApiLogRepository
import com.example.serviceMoniter.logs.repository.RateLimitHitRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ApiLogService(
    private val apiLogRepository: ApiLogRepository,
    private val rateLimitHitRepository: RateLimitHitRepository
) {
    
    fun saveLog(log: ApiLog): ApiLog {
        val savedLog = apiLogRepository.save(log)
        
        // Check if this is a rate limit hit
        if (isRateLimitHit(log)) {
            val rateLimitHit = RateLimitHit(
                service = log.service,
                endpoint = log.endpoint,
                timestamp = log.timestamp ?: Instant.now(),
                reason = "rate-limit-hit"
            )
            rateLimitHitRepository.save(rateLimitHit)
            println("Saved rate limit hit: $rateLimitHit")
        }
        
        return savedLog
    }
    
    fun getAllLogs(): List<ApiLog> {
        return apiLogRepository.findAll()
    }

    fun getAllRateLimitHits(): List<RateLimitHit> {
        return rateLimitHitRepository.findAll()
    }
    
    private fun isRateLimitHit(log: ApiLog): Boolean {
        // Check if extra map contains "rateLimitHit" key with true value
        if (log.extra != null && log.extra.containsKey("rateLimitHit")) {
            val rateLimitHit = log.extra["rateLimitHit"]
            if (rateLimitHit is Boolean && rateLimitHit) {
                return true
            }
        }
        
        // Check if note field contains "rate-limit-hit"
        if (log.extra != null && log.extra.containsKey("note")) {
            val note = log.extra["note"]
            if (note is String && note.contains("rate-limit-hit", ignoreCase = true)) {
                return true
            }
        }
        
        return false
    }
}
