package com.example.trendingmoviesapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.trendingmoviesapp.ui.navigation.AppScreens
import com.example.trendingmoviesapp.viewmodel.AppViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(appViewModel: AppViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Main Screen") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(AppScreens.ApiKeyScreen.route) }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            appViewModel.moviesList.forEachIndexed { index, movie ->
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .clickable {
                            appViewModel.selectedMovieIndex = index
                            navController.navigate(AppScreens.DetailScreen.route)
                        }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(AppViewModel(), rememberNavController())
}