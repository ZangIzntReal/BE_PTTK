package com.example.be.config

import com.example.be.model.DataSet
import com.example.be.model.TypeModel
import com.example.be.repository.DataSetRepository
import com.example.be.repository.TypeModelRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseInitializer {
    
    @Bean
    fun initDatabase(
        typeModelRepository: TypeModelRepository,
        dataSetRepository: DataSetRepository
    ): CommandLineRunner {
        return CommandLineRunner { args ->
            // Initialize only if tables are empty
            if (typeModelRepository.count() == 0L) {
                // Add sample model types
                val cnn = typeModelRepository.save(TypeModel(
                    name = "Convolutional Neural Network",
                    description = "Deep learning algorithm which can take in an input image, assign importance to various aspects in the image and be able to differentiate one from the other.",
                    folderModel = "/models/cnn"
                ))
                
                val lstm = typeModelRepository.save(TypeModel(
                    name = "Long Short-Term Memory",
                    description = "Special kind of RNN, capable of learning long-term dependencies.",
                    folderModel = "/models/lstm"
                ))
                
                val svm = typeModelRepository.save(TypeModel(
                    name = "Support Vector Machine",
                    description = "Supervised learning models with associated learning algorithms that analyze data for classification and regression analysis.",
                    folderModel = "/models/svm"
                ))
                
                println("Sample model types initialized")
            }
            
            if (dataSetRepository.count() == 0L) {
                // Add sample datasets
                val mnist = dataSetRepository.save(DataSet(
                    name = "MNIST",
                    description = "Handwritten digits dataset"
                ))
                
                val cifar = dataSetRepository.save(DataSet(
                    name = "CIFAR-10",
                    description = "Dataset of 60,000 32x32 color images in 10 classes"
                ))
                
                val imdb = dataSetRepository.save(DataSet(
                    name = "IMDB Reviews",
                    description = "Dataset for binary sentiment classification"
                ))
                
                println("Sample datasets initialized")
            }
        }
    }
} 