package com.example.BE_PTTK.service

import com.example.BE_PTTK.exception.ResourceNotFoundException
import com.example.BE_PTTK.model.TrainedModel
import com.example.BE_PTTK.repository.TrainedModelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManageService(
    private val trainedModelRepository: TrainedModelRepository
) {
    fun getAllTrainedModel(): List<TrainedModel> {
        return trainedModelRepository.findAll()
    }
    
    @Transactional
    fun updateTrainedModel(model: TrainedModel): Boolean {
        val existingModel = trainedModelRepository.findById(model.id)
            ?: throw ResourceNotFoundException("TrainedModel with ID ${model.id} not found")
            
        // Update fields that are allowed to be modified
        existingModel.name = model.name
        existingModel.description = model.description
        existingModel.accuracy = model.accuracy
        existingModel.precision = model.precision
        existingModel.recall = model.recall
        existingModel.f1Score = model.f1Score
        
        trainedModelRepository.save(existingModel)
        return true
    }
    
    @Transactional
    fun deleteTrainedModel(id: Int): Boolean {
        if (!trainedModelRepository.existsById(id)) {
            throw ResourceNotFoundException("TrainedModel with ID $id not found")
        }
        
        trainedModelRepository.deleteById(id)
        return true
    }
} 