package com.example.serviceMoniter.meta.service

import com.example.serviceMoniter.meta.model.ResolveStatus
import com.example.serviceMoniter.meta.repository.ResolveStatusRepository
import org.springframework.stereotype.Service

@Service
class ResolveStatusService(
    private val resolveStatusRepository: ResolveStatusRepository
) {
    fun saveResolveStatus(userId: String, resolvedLogId: String): ResolveStatus {
        val resolveStatus = ResolveStatus(
            userId = userId,
            resolvedLogId = resolvedLogId
        )
        val saved = resolveStatusRepository.save(resolveStatus)
        println("Saved resolve status: $saved")
        return saved
    }

    fun getAllResolveStatuses(): List<ResolveStatus> {
        return resolveStatusRepository.findAll()
    }

    fun getResolveStatusesByUserId(userId: String): List<ResolveStatus> {
        return resolveStatusRepository.findAll().filter { it.userId == userId }
    }
}
