# ML Model Training API

This is a Spring Boot application for managing machine learning model training and trained models. It provides a REST API for training models, fetching model types, datasets, and managing trained models.

## Architecture

The application follows a multi-layered architecture:

1. **Controller Layer**: Handles HTTP requests from clients
2. **Service Layer**: Contains business logic
3. **Repository Layer**: Interacts with MySQL database

## Features

- Train machine learning models using different model types and datasets
- Retrieve all available model types and datasets
- Retrieve all trained models
- Update and delete trained models

## API Endpoints

### Train Controller

- `POST /api/train/model?typeId={typeId}&dataSetId={dataSetId}`: Train a model using specified model type and dataset
- `GET /api/train/types`: Get all available model types
- `GET /api/train/datasets`: Get all available datasets

### Manage Controller

- `GET /api/manage/models`: Get all trained models
- `PUT /api/manage/model`: Update a trained model
- `DELETE /api/manage/model/{id}`: Delete a trained model

## Setup

### Prerequisites

- Java 17 or higher
- MySQL database

### Configuration

The application is configured to connect to a MySQL database. You can update the database configuration in `src/main/resources/application.properties`.
