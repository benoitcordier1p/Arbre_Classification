package com.example.data.models

data class TreesComplete(
    val facet_groups: List<FacetGroup>,
    val nhits: Int,
    val parameters: Parameters,
    val records: List<Record>
)

