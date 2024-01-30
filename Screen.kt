package com.example.pokemonworld

sealed class Screen(val route:String) {
    object HomeScreen: Screen("homescreen")
    object DetailScreen: Screen("detailscreen")

}