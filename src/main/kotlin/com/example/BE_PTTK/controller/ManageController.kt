package com.example.BE_PTTK.controller

import com.example.BE_PTTK.model.TrainedModel
import com.example.BE_PTTK.service.ManageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/manage")
class ManageController(
    private val manageService: ManageService
) {
    @GetMapping("/models")
    fun getAllTrainedModel(): ResponseEntity<List<TrainedModel>> {
        return ResponseEntity.ok(manageService.getAllTrainedModel())
    }
    
    @PutMapping("/model")
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