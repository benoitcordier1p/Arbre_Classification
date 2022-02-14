package com.example.data.models

data class TreesComplete(
    val facet_groups: List<FacetGroup>,
    val nhits: Int,
    val parameters: Parameters,
    val records: List<Record>
)

fun TreesComplete.toTreeList() : List<Tree>{
    val treeList : MutableList<Tree> = mutableListOf()
    records.forEach { record ->
        treeList.add(
            Tree(
                id = record.recordid,
                adresse = "${record.fields.adresse}, ${record.fields.arrondissement}",
                circonferenceencm = record.fields.circonferenceencm,
                hauteurenm = record.fields.hauteurenm,
                espece = record.fields.espece ?: "Unknown"
        )
        )
    }
    return treeList.toList()
}