package com.example.serviceMoniter.meta.controller

import com.example.serviceMoniter.meta.model.ResolveStatus
import com.example.serviceMoniter.meta.service.ResolveStatusService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class ResolveStatusRequest(
    val userId: String,
    val resolvedLogId: String
)

data class ResolveStatusResponse(
    val status: String,
    val message: String,
    val id: String? = null
)

@RestController
@RequestMapping("/api/meta")
class ResolveStatusController(
    private val resolveStatusService: ResolveStatusService
) {

    @PostMapping("/resolve-status")
    fun saveResolveStatus(@RequestBody request: ResolveStatusRequest): ResponseEntity<ResolveStatusResponse> {
        return try {
            val resolveStatus = resolveStatusService.saveResolveStatus(request.userId, request.resolvedLogId)
            println("Saved resolve status for user ${request.userId} and log ${request.resolvedLogId}")
            ResponseEntity.ok(
                ResolveStatusResponse(
                    status = "success",
                    message = "Resolve status saved successfully",
                    id = resolveStatus.id
                )
            )
        } catch (e: Exception) {
            println("Error saving resolve status: ${e.message}")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResolveStatusResponse(
                    status = "error",
                    message = "Failed to save resolve status: ${e.message}"
                )
            )
        }
    }

    @GetMapping("/resolve-status")
    fun getAllResolveStatuses(): ResponseEntity<List<ResolveStatus>> {
        return try {
            val statuses = resolveStatusService.getAllResolveStatuses()
            ResponseEntity.ok(statuses)
        } catch (e: Exception) {
            println("Error fetching resolve statuses: ${e.message}")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @GetMapping("/resolve-status/user/{userId}")
    fun getResolveStatusesByUserId(@PathVariable userId: String): ResponseEntity<List<ResolveStatus>> {
        return try {
            val statuses = resolveStatusService.getResolveStatusesByUserId(userId)
            ResponseEntity.ok(statuses)
        } catch (e: Exception) {
            println("Error fetching resolve statuses for user: ${e.message}")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }
}
