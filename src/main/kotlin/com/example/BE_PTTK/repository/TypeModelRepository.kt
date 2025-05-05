package com.example.BE_PTTK.repository

import com.example.BE_PTTK.model.TypeModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TypeModelRepository : JpaRepository<TypeModel, Int> {
    override fun findAll(): List<TypeModel>
    override fun findById(id: Int): Optional<TypeModel?>
} 