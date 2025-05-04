package com.example.BE_PTTK.repository

import com.example.BE_PTTK.model.DataSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DataSetRepository : JpaRepository<DataSet, Int> {
    fun findAll(): List<DataSet>
    fun findById(id: Int): DataSet?
} 