package com.example.serviceMoniter.logs.controller

import org.springframework.web.bind.annotation.CrossOrigin
import com.example.serviceMoniter.logs.model.ApiLog
import com.example.serviceMoniter.logs.repository.ApiLogRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"]) 
@RestController
@RequestMapping("/collector")
class IngestController(private val apiLogRepository: ApiLogRepository) {
    
    @PostMapping("/logs")
    fun receiveLog(@RequestBody log: ApiLog): ResponseEntity<Map<String, String>> {
        try {
            val savedLog = apiLogRepository.save(log)
            println("Saved log to database: $savedLog")
            return ResponseEntity.ok(mapOf("status" to "success", "message" to "Log saved successfully", "id" to (savedLog.id ?: "")))
        } catch (e: Exception) {
            println("Error saving log: ${e.message}")
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("status" to "error", "message" to "Failed to save log: ${e.message}"))
        }
    }

    @GetMapping("/logs")
    fun getAllLogs(): ResponseEntity<List<ApiLog>> {
        return try {
            val logs = apiLogRepository.findAll()
            ResponseEntity.ok(logs)
        } catch (e: Exception) {
            println("Error fetching logs: ${e.message}")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }
}