package com.example.appdatabinding.presentation.addTree

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.domain.models.Tree

@Composable
fun Form(tree: Tree?){

    val idText = rememberSaveable {
        tree?.let { mutableStateOf(tree.id) } ?: mutableStateOf("")
    }
    val especeText = rememberSaveable {
        tree?.let { mutableStateOf(tree.espece) } ?: mutableStateOf("")
    }
    val hauteurText = rememberSaveable {
        tree?.let { mutableStateOf(tree.hauteurenm.toString()) } ?: mutableStateOf("")
    }
    val circonfText = rememberSaveable {
        tree?.let { mutableStateOf(tree.circonferenceencm.toString()) } ?: mutableStateOf("")
    }
    val adresseText = rememberSaveable {
        tree?.let { mutableStateOf(tree.adresse) } ?: mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFACB992))
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = idText.value,
            onValueChange = {
                idText.value = it
            },
            label = { Text("id") },
            modifier = Modifier.testTag("input_id")
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = especeText.value,
            onValueChange = {
                especeText.value = it
            },
            label = { Text("espèce") },
            modifier = Modifier.testTag("input_espece")
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = hauteurText.value,
            onValueChange = {
                hauteurText.value = it
            },
            label = { Text("hauteur") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.testTag("input_hauteur")
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = circonfText.value,
            onValueChange = {
                circonfText.value = it
            },
            label = { Text("circonférence") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.testTag("input_circonference")
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = adresseText.value,
            onValueChange = {
                adresseText.value = it
            },
            label = { Text("adresse") },
            modifier = Modifier.testTag("input_adresse")
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
                listOf(
                    Tree(
                        id = idText.value,
                        espece = especeText.value,
                        hauteurenm = hauteurText.value.toInt(),
                        circonferenceencm = circonfText.value.toInt(),
                        adresse = adresseText.value
                    )
                )
        }) {
            Text(
                text = "Save",
                modifier = Modifier.testTag("Button_Save")
            )
        }
    }

}