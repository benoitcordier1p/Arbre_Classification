package com.example.arbre_classification.presentation.treesList.components.treesListScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.arbre_classification.presentation.treesList.TreesListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Destination(start = true)
@Composable
fun TreesListScreen(
    viewModel: TreesListViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val state = remember { viewModel.state }
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }
    val endReached by remember { viewModel.endReached }
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val offline by remember { viewModel.offline }

    Scaffold {
        Text(
            text = error,
            color = MaterialTheme.colors.error,
            modifier = Modifier.alpha(if (error.isNotEmpty()) 1f else 0f)
        )
        Text(
            text = "You are currently offline",
            color = MaterialTheme.colors.error,
            modifier = Modifier.alpha(if (error.isEmpty() && offline) 1f else 0f)
        )
        SearchBar(
            hint = "Search...",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset(y = 20.dp)
        ) {
            viewModel.searchTree(it)
        }
        SwipeRefresh(
            state = SwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.forceRefresh() }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 100.dp)
                    .background(MaterialTheme.colors.background)
                    .testTag("Tree_List")
            ) {
                items(state.value.size) {
                    println(state.value.size)
                    if (it >= state.value.size - 1 && !offline && !endReached) {
                        LaunchedEffect(key1 = Unit, block = {
                            viewModel.getTrees(false)
                        })
                    }
                    val dismissState = rememberDismissState()
                    if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
                        LaunchedEffect(key1 = Unit, block = {
                            viewModel.deleteTree(it)
                            viewModel.forceRefresh()
                            dismissState.snapTo(DismissValue.Default)
                        })
                    }
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.StartToEnd),
                        background = {
                            val color by animateColorAsState(
                                targetValue = when (dismissState.targetValue) {
                                    DismissValue.Default -> MaterialTheme.colors.background
                                    DismissValue.DismissedToEnd -> MaterialTheme.colors.primary
                                    DismissValue.DismissedToStart -> MaterialTheme.colors.primary
                                }
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = color)
                            )
                        },
                        dismissContent = {
                            Box(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .testTag("Tree_Item_$it")
                            ) {
                                if (it < state.value.size) {
                                    TreeListItem(tree = state.value[it], navigator = navigator)
                                }

                            }
                        },
                    )
                }
            }
        }
        LoadingErrorScreen(
            isLoading = isLoading
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    val focusRequester = FocusRequester()

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { isHintDisplayed = !it.isFocused }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun LoadingErrorScreen(isLoading: Boolean) {
    Column {
        if (isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

