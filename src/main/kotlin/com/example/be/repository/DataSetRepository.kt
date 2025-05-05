package com.example.be.repository

import com.example.be.model.DataSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DataSetRepository : JpaRepository<DataSet, Int> {
    override fun findAll(): List<DataSet>
    override fun findById(id: Int): Optional<DataSet?>
} 