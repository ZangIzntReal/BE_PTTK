package com.example.BE_PTTK.repository

import com.example.BE_PTTK.model.TrainedModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainedModelRepository : JpaRepository<TrainedModel, Int> {
    fun findAll(): List<TrainedModel>
    fun findById(id: Int): TrainedModel?
} 