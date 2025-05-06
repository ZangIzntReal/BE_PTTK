package com.example.be.service

import com.example.be.exception.ResourceNotFoundException
import com.example.be.model.DataSet
import com.example.be.model.TrainedModel
import com.example.be.model.TypeModel
import com.example.be.repository.DataSetRepository
import com.example.be.repository.TrainedModelRepository
import com.example.be.repository.TypeModelRepository
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
    fun trainModel(
        typeId: Int, 
        dataSetId: Int, 
        customName: String? = null, 
        customVersion: String? = null
    ): Boolean {
        val typeModel = typeModelRepository.findById(typeId).get()
            ?: throw ResourceNotFoundException("TypeModel with ID $typeId not found")
            
        val dataSet = dataSetRepository.findById(dataSetId).get()
            ?: throw ResourceNotFoundException("DataSet with ID $dataSetId not found")
            
        // Create a new trained model instance
        val filePath = "${typeModel.folderModel}/${UUID.randomUUID()}.model"
        val modelName = customName ?: "${typeModel.name} - ${dataSet.name}"
        val modelVersion = customVersion ?: "1.0.0" // Default version if not provided
            
        val trainedModel = TrainedModel(
            name = modelName,
            version = modelVersion,
            modelType = typeModel,
            dataSet = dataSet,
            filePath = filePath
        )
        
        // Save the model
        val savedModel = trainedModelRepository.save(trainedModel)
        
        // Start training asynchronously
        GlobalScope.launch {
            try {
                // Simulate model training (in a real application, this would be actual ML model training)
                delay(5000) // Simulate 5 seconds of training
                
                // Update model status after training
                val updatedModel = trainedModelRepository.findById(savedModel.id).get()
                if (updatedModel != null) {
                    updatedModel.accuracy = 0.85 // Simulated accuracy
                    updatedModel.precision = 0.83 // Simulated precision
                    updatedModel.recall = 0.87 // Simulated recall
                    updatedModel.f1Score = 0.85 // Simulated F1 score
                    trainedModelRepository.save(updatedModel)
                }
            } catch (e: Exception) {
                // Handle training failure
                val updatedModel = trainedModelRepository.findById(savedModel.id).get()
                if (updatedModel != null) {
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