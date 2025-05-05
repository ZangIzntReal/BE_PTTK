package com.example.be.repository

import com.example.be.model.TrainedModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TrainedModelRepository : JpaRepository<TrainedModel, Int> {
    override fun findAll(): List<TrainedModel>
    override fun findById(id: Int): Optional<TrainedModel?>
} 