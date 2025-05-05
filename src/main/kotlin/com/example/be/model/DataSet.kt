package com.example.be.model

import jakarta.persistence.*

@Entity
@Table(name = "data_sets")
data class DataSet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    
    @Column(nullable = false)
    val name: String,
    
    @Column
    val description: String? = null,
    
    @OneToMany(mappedBy = "dataSet", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val trainedModels: MutableSet<TrainedModel> = mutableSetOf()
) 