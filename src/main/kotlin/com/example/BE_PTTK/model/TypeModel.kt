package com.example.BE_PTTK.model

import jakarta.persistence.*

@Entity
@Table(name = "type_models")
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
    
    @OneToMany(mappedBy = "typeModel", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val trainedModels: MutableSet<TrainedModel> = mutableSetOf()
) 