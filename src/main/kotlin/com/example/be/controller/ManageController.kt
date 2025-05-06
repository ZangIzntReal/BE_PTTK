package com.example.be.controller

import com.example.be.model.TrainedModel
import com.example.be.service.ManageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/manage")
@Tag(name = "Manage API", description = "APIs for managing trained models")
class ManageController(
    private val manageService: ManageService
) {
    @GetMapping("/models")
    @Operation(summary = "Get all trained models", description = "Returns a list of all trained models")
    fun getAllTrainedModel(): ResponseEntity<List<TrainedModel>> {
        return ResponseEntity.ok(manageService.getAllTrainedModel())
    }
    
    @PutMapping("/model")
    @Operation(summary = "Update a trained model", description = "Updates model details such as name, description, and metrics")
    fun updateTrainedModel(@RequestBody model: TrainedModel): ResponseEntity<Boolean> {
        return try {
            val result = manageService.updateTrainedModel(model)
            ResponseEntity.ok(true)
        } catch (e: Exception) {
            // Exception will be handled by the global exception handler
            throw e
        }
    }
    
    @DeleteMapping("/model/{id}")
    @Operation(summary = "Delete a trained model", description = "Deletes a trained model by its ID")
    fun deleteTrainedModel(@PathVariable id: Int): ResponseEntity<Boolean> {
        return try {
            val result = manageService.deleteTrainedModel(id)
            ResponseEntity.ok(true)
        } catch (e: Exception) {
            // Exception will be handled by the global exception handler
            throw e
        }
    }
} 