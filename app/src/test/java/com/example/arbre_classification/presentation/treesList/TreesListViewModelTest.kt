package com.example.arbre_classification.presentation.treesList

import androidx.compose.runtime.mutableStateOf
import com.example.arbre_classification.domain.repository.FakeTreeRepository
import com.example.arbre_classification.domain.use_case.treesListUseCase.GetTreesUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TreesListViewModelTest {

    private lateinit var getTrees : GetTreesUseCase
    private lateinit var fakeTreeRepository: FakeTreeRepository



    @Before
    fun setUp(){
        fakeTreeRepository= FakeTreeRepository()
        getTrees = GetTreesUseCase(fakeTreeRepository)
    }

    @Test
    fun `position of tree is correct`() {
        GlobalScope.launch {
            val trees=fakeTreeRepository.getTrees()
            val viewModel = TreesListViewModel(getTrees)
            viewModel.state= mutableStateOf(TreesListState(trees))
            val position=viewModel.getTreePosition("2")
            assertEquals(position,2)
        }
    }
}