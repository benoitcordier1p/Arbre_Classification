package com.example.domain.useCase.treesListUseCase

import com.example.data.remote.errorHandler.ErrorHandler
import com.example.data.remote.errorHandler.ErrorHandlerImpl
import com.example.domain.fetchstrategy.FetchStrategy
import com.example.data.repository.FakeRepositoryLocal
import com.example.data.repository.FakeRepositoryRemote
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTreesUseCaseTest {

    private lateinit var getTrees : GetTreesUseCase
    private lateinit var fakeRepositoryLocal: FakeRepositoryLocal
    private lateinit var fakeRepositoryRemote: FakeRepositoryRemote
    private lateinit var errorHandler: ErrorHandler

    @Before
    fun setUp(){
        fakeRepositoryLocal = FakeRepositoryLocal()
        fakeRepositoryRemote = FakeRepositoryRemote()
        errorHandler = ErrorHandlerImpl()
        getTrees = GetTreesUseCase(fakeRepositoryLocal,fakeRepositoryRemote,errorHandler)
    }

    @Test
    fun get_Trees_From_FetchStrategy() = runBlocking {
        val treeCache = getTrees(0,FetchStrategy.Cache).first()
        val treeRemote = getTrees(0, FetchStrategy.Remote).first()
        val treeLocal = getTrees(0, FetchStrategy.Local).first()
        assertEquals(treeRemote.data?.get(0)?.id,"1")
        assertEquals(treeCache.data?.get(0)?.id,"2")
        assertEquals(treeLocal.data?.get(0)?.id,"3")
    }
}