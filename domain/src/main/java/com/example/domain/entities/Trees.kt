package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.Tree

@Entity
data class Trees(

    @PrimaryKey(autoGenerate = false)
    val id : String,
    val espece : String,
    val hauteur : Int,
    val circonference : Int,
    val adresse : String

)

fun Trees.toDomain(): Tree = Tree(
    id = id,
    adresse = adresse,
    circonferenceencm = circonference,
    hauteurenm = hauteur,
    espece = espece
)

