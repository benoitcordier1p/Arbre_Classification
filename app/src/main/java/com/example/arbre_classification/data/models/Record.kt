package com.example.arbre_classification.data.models

import com.example.arbre_classification.domain.models.Tree

data class Record(
    val datasetid: String,
    val fields: Fields,
    val geometry: Geometry,
    val record_timestamp: String,
    val recordid: String
)

