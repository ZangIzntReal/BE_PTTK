package com.example.BE_PTTK.controller

import com.example.BE_PTTK.model.DataSet
import com.example.BE_PTTK.model.TypeModel
import com.example.BE_PTTK.service.TrainService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/train")
class TrainController(
    private val trainService: TrainService
) {
    @PostMapping("/model")
    fun trainModel(
        @RequestParam typeId: Int,
        @RequestParam dataSetId: Int
    ): ResponseEntity<Boolean> {
        return try {
            val result = trainService.trainModel(typeId, dataSetId)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            // Exception will be handled by the global exception handler
            throw e
        }
    }
    
    @GetMapping("/types")
    fun getAllTypeModel(): ResponseEntity<List<TypeModel>> {
        return ResponseEntity.ok(trainService.getAllTypeModel())
    }
    
    @GetMapping("/datasets")
    fun getAllDataSet(): ResponseEntity<List<DataSet>> {
        return ResponseEntity.ok(trainService.getAllDataSet())
    }
}