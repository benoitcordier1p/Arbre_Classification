package com.example.arbre_classification.presentation.treesList

import com.example.domain.repository.FakeTreeRepository
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test

class TreesListViewModelTest {

    private lateinit var getTrees : com.example.domain.useCase.treesListUseCase.GetTreesUseCase
    private lateinit var fakeTreeRepository: FakeTreeRepository



    @Before
    fun setUp(){
        fakeTreeRepository= FakeTreeRepository()
        getTrees = com.example.domain.useCase.treesListUseCase.GetTreesUseCase(fakeTreeRepository)
    }

    @Test
    fun `position of tree is correct`() {
        GlobalScope.launch {
            val trees=fakeTreeRepository.getTrees()
            val viewModel = TreesListViewModel(getTrees)
        }
    }
}