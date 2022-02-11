package com.example.arbre_classification.domain.models

data class Tree(
    val id : String,
    val adresse: String,
    val circonferenceencm: Int,
    val espece: String,
    val hauteurenm: Int
)

class FakeTree{
    fun mockTree(): Tree {
        return Tree(
            "1234567890",
            "1 Boulevard de Clichy, 18ème Arrondissement",
            135,
            "hispanica",
            2
        )
    }
}



