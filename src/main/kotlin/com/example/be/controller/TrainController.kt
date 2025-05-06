package com.example.be.controller

import com.example.be.model.DataSet
import com.example.be.model.TypeModel
import com.example.be.service.TrainService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/train")
@Tag(name = "Train API", description = "APIs for training models and managing model types and datasets")
class TrainController(
    private val trainService: TrainService
) {
    @PostMapping("/model")
    @Operation(summary = "Train a new model", description = "Trains a new model based on the type and dataset provided")
    fun trainModel(
        @RequestParam typeId: Int,
        @RequestParam dataSetId: Int,
        @RequestParam(required = false) name: String? = null,
        @RequestParam(required = false) version: String? = null
    ): ResponseEntity<Boolean> {
        return try {
            val result = trainService.trainModel(typeId, dataSetId, name, version)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            // Exception will be handled by the global exception handler
            throw e
        }
    }
    
    @GetMapping("/types")
    @Operation(summary = "Get all model types", description = "Returns a list of all available model types")
    fun getAllTypeModel(): ResponseEntity<List<TypeModel>> {
        return ResponseEntity.ok(trainService.getAllTypeModel())
    }
    
    @GetMapping("/datasets")
    @Operation(summary = "Get all datasets", description = "Returns a list of all available datasets")
    fun getAllDataSet(): ResponseEntity<List<DataSet>> {
        return ResponseEntity.ok(trainService.getAllDataSet())
    }
}