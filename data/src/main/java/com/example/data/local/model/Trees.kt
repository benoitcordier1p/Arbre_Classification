package com.example.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trees(

    @PrimaryKey(autoGenerate = false)
    val id : String,
    val espece : String?,
    val hauteur : Int,
    val circonference : Int,
    val adresse : String

)

