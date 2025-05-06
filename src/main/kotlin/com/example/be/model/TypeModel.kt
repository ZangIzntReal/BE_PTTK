package com.example.be.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@Entity
@Table(name = "type_models")
@JsonIgnoreProperties("trainedModels")
data class TypeModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    
    @Column(nullable = false)
    val name: String,
    
    @Column
    val description: String? = null,
    
    @Column(nullable = false)
    val folderModel: String,
) 