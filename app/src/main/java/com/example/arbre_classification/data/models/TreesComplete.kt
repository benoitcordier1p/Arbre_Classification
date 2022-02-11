package com.example.arbre_classification.data.models

import com.example.arbre_classification.domain.models.Tree

data class TreesComplete(
    val facet_groups: List<FacetGroup>,
    val nhits: Int,
    val parameters: Parameters,
    val records: List<Record>
)

fun TreesComplete.toTreeList() : List<Tree>{
    val treeList : MutableList<Tree> = mutableListOf()
    records.forEach { record ->
        treeList.add(Tree(
            id = record.recordid,
            adresse = "${record.fields.adresse}, ${record.fields.arrondissement}",
            circonferenceencm = record.fields.circonferenceencm,
            hauteurenm = record.fields.hauteurenm,
            espece = record.fields.espece ?: "Unknown"
        ))
    }
    return treeList.toList()
}