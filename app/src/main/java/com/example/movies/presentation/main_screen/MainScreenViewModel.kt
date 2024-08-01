package com.example.movies.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movies.ItemsApplication
import com.example.movies.domain.model.Item
import com.example.movies.domain.model.Response
import com.example.movies.domain.repository.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val itemsRepository: ItemsRepository
): ViewModel() {

    private val _itemsResponse = MutableStateFlow<Response<List<Item>>>(Response.Loading)
    val itemsResponse = _itemsResponse.asStateFlow()

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            itemsRepository.getItems().collect {
                _itemsResponse.value = it
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val itemsRepository = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ItemsApplication).container.itemsRepository
                MainScreenViewModel(itemsRepository)
            }
        }
    }


}