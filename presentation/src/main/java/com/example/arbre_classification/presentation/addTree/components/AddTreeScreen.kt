package com.example.arbre_classification.presentation.addTree.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arbre_classification.presentation.addTree.AddTreeViewModel
import com.example.arbre_classification.util.WindowInfo
import com.example.arbre_classification.util.rememberWindowInfo
import com.example.domain.models.Tree
import com.ramcosta.composedestinations.annotation.Destination

@Destination(route = "add_tree_screen")
@Composable
fun AddTreeScreen(
    addTreeViewModel: AddTreeViewModel = hiltViewModel()
) {

    val windowInfo = rememberWindowInfo()
    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
        AddTreeCompactScreen(addTreeViewModel = addTreeViewModel)
    }
    else{
        AddTreeLargeScreen(addTreeViewModel = addTreeViewModel)
    }

}

@Composable
fun AddTreeCompactScreen(addTreeViewModel: AddTreeViewModel){
    val idText = rememberSaveable { mutableStateOf("") }
    val especeText = rememberSaveable { mutableStateOf("") }
    val hauteurText = rememberSaveable { mutableStateOf("") }
    val circonfText = rememberSaveable { mutableStateOf("") }
    val adresseText = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Text(text = "id")
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = idText.value,
            onValueChange = {
                idText.value = it
            },
            label = { Text("id") },
            modifier = Modifier.testTag("input_id")
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "espèce")
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = especeText.value,
            onValueChange = {
                especeText.value = it
            },
            label = { Text("espèce") },
            modifier = Modifier.testTag("input_espece")
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "hauteur")
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
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "circonférence")
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
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "adresse")
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
            addTreeViewModel.addTree(
                listOf(
                    Tree(
                        id = idText.value,
                        espece = especeText.value,
                        hauteurenm = hauteurText.value.toInt(),
                        circonferenceencm = circonfText.value.toInt(),
                        adresse = adresseText.value
                    )
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

@Composable
fun AddTreeLargeScreen(addTreeViewModel: AddTreeViewModel){
    val idText = rememberSaveable { mutableStateOf("") }
    val especeText = rememberSaveable { mutableStateOf("") }
    val hauteurText = rememberSaveable { mutableStateOf("") }
    val circonfText = rememberSaveable { mutableStateOf("") }
    val adresseText = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Row {
            Column(modifier = Modifier.weight(0.5F)) {
                Text(text = "id")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = idText.value,
                    onValueChange = {
                        idText.value = it
                    },
                    label = { Text("id") },
                    modifier = Modifier.testTag("input_id")
                )
            }
            Column(modifier = Modifier.weight(0.5F)) {
                Text(text = "espèce")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = especeText.value,
                    onValueChange = {
                        especeText.value = it
                    },
                    label = { Text("espèce") },
                    modifier = Modifier.testTag("input_espece")
                )
            }
        }
        Row {
            Column(modifier = Modifier.weight(0.5F)) {
                Text(text = "hauteur")
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
            }
            Column(modifier = Modifier.weight(0.5F)) {
                Text(text = "circonférence")
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
            }
        }
        Row {
            Column {
                Text(text = "adresse")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = adresseText.value,
                    onValueChange = {
                        adresseText.value = it
                    },
                    label = { Text("adresse") },
                    modifier = Modifier.testTag("input_adresse")
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            addTreeViewModel.addTree(
                listOf(
                    Tree(
                        id = idText.value,
                        espece = especeText.value,
                        hauteurenm = hauteurText.value.toInt(),
                        circonferenceencm = circonfText.value.toInt(),
                        adresse = adresseText.value
                    )
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