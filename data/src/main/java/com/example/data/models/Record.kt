package com.example.data.models

import com.example.domain.models.Tree

data class Record(
    val datasetid: String,
    val fields: Fields,
    val geometry: Geometry,
    val record_timestamp: String,
    val recordid: String
)

fun Record.toDomain(): Tree = Tree(
    id = recordid,
    adresse = "${fields.adresse}, ${fields.arrondissement}",
    circonferenceencm = fields.circonferenceencm,
    hauteurenm = fields.hauteurenm,
    espece = fields.espece ?: "Unknown"
)


