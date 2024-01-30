package com.example.pokemonworld

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val pokemonViewModel:MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route){
            HomeScreen(
                navitgateToDetail = { navController.navigate(Screen.DetailScreen.route + "/${it}") },
                viewModel = pokemonViewModel
            )
        }
        composable(Screen.DetailScreen.route + "/{name}"){
            val name = navController.currentBackStackEntry?.arguments?.getString("name")?: "no name"
            DetailScreen(navigateBack = {navController.navigateUp()},pokemonViewModel,name)

    }
}}