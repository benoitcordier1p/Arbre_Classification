package com.example.domain.entities

import com.example.domain.models.Tree
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Trees(
    @PrimaryKey
    var id: String = "",
    @Required
    var espece: String = "",
    var hauteur: Int = 0,
    var circonference: Int = 0,
    var adresse : String = ""
): RealmObject()

fun Trees.toDomain(): Tree = Tree(
    id = id,
    adresse = adresse,
    circonferenceencm = circonference,
    hauteurenm = hauteur,
    espece = espece
)