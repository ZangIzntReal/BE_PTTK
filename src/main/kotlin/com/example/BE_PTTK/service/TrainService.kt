package com.example.BE_PTTK.service

import com.example.BE_PTTK.exception.ResourceNotFoundException
import com.example.BE_PTTK.model.DataSet
import com.example.BE_PTTK.model.TrainedModel
import com.example.BE_PTTK.model.TypeModel
import com.example.BE_PTTK.repository.DataSetRepository
import com.example.BE_PTTK.repository.TrainedModelRepository
import com.example.BE_PTTK.repository.TypeModelRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID
import kotlinx.coroutines.*

@Service
class TrainService(
    private val typeModelRepository: TypeModelRepository,
    private val dataSetRepository: DataSetRepository,
    private val trainedModelRepository: TrainedModelRepository
) {
    @Transactional
    fun trainModel(typeId: Int, dataSetId: Int): Boolean {
        val typeModel = typeModelRepository.findById(typeId) 
            ?: throw ResourceNotFoundException("TypeModel with ID $typeId not found")
            
        val dataSet = dataSetRepository.findById(dataSetId) 
            ?: throw ResourceNotFoundException("DataSet with ID $dataSetId not found")
            
        // Create a new trained model instance
        val filePath = "${typeModel.folderModel}/${UUID.randomUUID()}.model"
        val trainedModel = TrainedModel(
            name = "${typeModel.name} - ${dataSet.name}",
            description = "Model trained with ${typeModel.name} using ${dataSet.name} dataset",
            version = "1.0.0", // Initial version
            typeModel = typeModel,
            dataSet = dataSet,
            filePath = filePath,
            status = "Training"
        )
        
        // Save the model
        val savedModel = trainedModelRepository.save(trainedModel)
        
        // Start training asynchronously
        GlobalScope.launch {
            try {
                // Simulate model training (in a real application, this would be actual ML model training)
                delay(5000) // Simulate 5 seconds of training
                
                // Update model status after training
                val updatedModel = trainedModelRepository.findById(savedModel.id)
                if (updatedModel != null) {
                    updatedModel.status = "Completed"
                    updatedModel.accuracy = 0.85 // Simulated accuracy
                    updatedModel.precision = 0.83 // Simulated precision
                    updatedModel.recall = 0.87 // Simulated recall
                    updatedModel.f1Score = 0.85 // Simulated F1 score
                    trainedModelRepository.save(updatedModel)
                }
            } catch (e: Exception) {
                // Handle training failure
                val updatedModel = trainedModelRepository.findById(savedModel.id)
                if (updatedModel != null) {
                    updatedModel.status = "Failed"
                    trainedModelRepository.save(updatedModel)
                }
            }
        }
        
        return true
    }
    
    fun getAllTypeModel(): List<TypeModel> {
        return typeModelRepository.findAll()
    }
    
    fun getAllDataSet(): List<DataSet> {
        return dataSetRepository.findAll()
    }
} 