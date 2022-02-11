package com.example.arbre_classification.data.models

data class Parameters(
    val dataset: String,
    val facet: List<String>,
    val format: String,
    val rows: Int,
    val start: Int,
    val timezone: String
)