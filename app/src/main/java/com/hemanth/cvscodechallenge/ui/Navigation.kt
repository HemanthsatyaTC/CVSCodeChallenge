package com.hemanth.cvscodechallenge.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hemanth.cvscodechallenge.ui.screens.DetailScreen
import com.hemanth.cvscodechallenge.ui.screens.SearchScreen
import com.hemanth.cvscodechallenge.viewmodel.ImageViewModel

@Composable
fun Navigation (
    navController: NavHostController = rememberNavController(),
    viewModel: ImageViewModel = hiltViewModel()
){
    NavHost(
        navController = navController,
        startDestination = "searchScreen"){
        composable("searchScreen") {
            SearchScreen( onItemClick = { item ->
                navController.navigate("detailScreen/${item.title}")
            }, viewModel = viewModel)
        }
        composable(
            route = "detailScreen/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            DetailScreen(title = title, viewModel = viewModel)
        }

    }

}