package com.example.domain.useCase.treesListUseCase

import com.example.data.remote.errorHandler.ErrorHandler
import com.example.data.remote.errorHandler.ErrorHandlerImpl
import com.example.data.repository.FakeRepositoryLocal
import com.example.data.repository.FakeRepositoryRemote
import com.example.domain.fetchstrategy.FetchStrategy
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.collect
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

    /*
    @Test
    fun get_Trees_From_FetchStrategy() = runBlocking {
        getTrees(0,FetchStrategy.Cache).collect {
            when(it){
                is Resource.Success -> assertEquals(it.data?.get(0)?.id,"2")
                else -> println("Error")
            }
        }
        getTrees(0, FetchStrategy.Remote).collect {
            when(it){
                is Resource.Success -> assertEquals(it.data?.get(0)?.id,"2")
                else -> println("Error")
            }
        }
        getTrees(0, FetchStrategy.Local).collect {
            when(it){
                is Resource.Success -> assertEquals(it.data?.get(0)?.id,"2")
                else -> println("Error")
            }
        }
    }
    */
}