package com.example.movies.presentation.main_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.components.FailureScreen
import com.example.movies.components.ProgressBar
import com.example.movies.domain.model.Response
import com.example.movies.presentation.main_screen.components.ItemsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory)
) {
    val itemsResponse by mainScreenViewModel.itemsResponse.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "OTT Platforms")
            })
        }
    ) { innerPadding ->
        when (itemsResponse) {
            is Response.Error -> {
                FailureScreen()
            }
            Response.Loading -> {
                ProgressBar()
            }
            is Response.Success -> {
                (itemsResponse as Response.Success).data?.let {
                    ItemsList(
                        itemsList = it,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}