package com.example.domain.models

import android.os.Parcelable
import com.example.data.local.model.Trees
import com.example.data.remote.models.Record
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tree(
    val id: String,
    val adresse: String,
    val circonferenceencm: Int,
    val espece: String,
    val hauteurenm: Int
) : Parcelable

fun mock(): Tree = Tree(
    "1234567890",
    "1 Boulevard de Clichy, 18ème Arrondissement",
    135,
    "hispanica",
    2
)

fun Record.toDomain(): Tree = Tree(
    id = recordid,
    adresse = "${fields.adresse}, ${fields.arrondissement}",
    circonferenceencm = fields.circonferenceencm,
    hauteurenm = fields.hauteurenm,
    espece = fields.espece ?: "Unknown"
)

fun Trees.toDomain(): Tree = Tree(
    id = id,
    adresse = adresse,
    circonferenceencm = circonference,
    hauteurenm = hauteur,
    espece = espece ?: "unknown"
)

fun Tree.toEntity() : Trees = Trees(
    id = id,
    adresse = adresse,
    circonference = circonferenceencm,
    hauteur = hauteurenm,
    espece = espece
)







