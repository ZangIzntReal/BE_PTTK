package com.example.BE_PTTK.repository

import com.example.BE_PTTK.model.TypeModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeModelRepository : JpaRepository<TypeModel, Int> {
    fun findAll(): List<TypeModel>
    fun findById(id: Int): TypeModel?
} 