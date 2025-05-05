package com.example.BE_PTTK.repository

import com.example.BE_PTTK.model.DataSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DataSetRepository : JpaRepository<DataSet, Int> {
    override fun findAll(): List<DataSet>
    override fun findById(id: Int): Optional<DataSet?>
} 