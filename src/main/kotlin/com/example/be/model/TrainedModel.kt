package com.example.be.model

import jakarta.persistence.*

@Entity
@Table(name = "trained_models")
data class TrainedModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    
    @Column(nullable = false)
    var name: String,
    
    @Column(nullable = false)
    val version: String,
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_model_id", nullable = false)
    val typeModel: TypeModel,
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "data_set_id", nullable = false)
    val dataSet: DataSet,
    
    @Column(nullable = false)
    val filePath: String,
    
    @Column
    var description: String? = null,
    
    @Column
    var accuracy: Double? = null,
    
    @Column
    var precision: Double? = null,
    
    @Column
    var recall: Double? = null,
    
    @Column
    var f1Score: Double? = null,
    
    @Column(nullable = false)
    var status: String = "Training" // Training, Completed, Failed
) 