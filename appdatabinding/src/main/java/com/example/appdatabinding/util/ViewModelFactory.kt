package com.example.appdatabinding.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase

class ViewModelFactory(val requestItemData: GetTreesUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetTreesUseCase::class.java).newInstance(requestItemData)
    }
}