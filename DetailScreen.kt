package com.example.pokemonworld

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokemonworld.data.PokemonDetail



//To take user input name pokemon
/*
* In your DetailScreen composable, observe the LiveData changes and
* call the fetch function when the screen is created*/

@Composable
fun DetailScreen(
    navigateBack:()->Unit,
    viewModel: MainViewModel,
    name:String?
){

   val viewState by viewModel.pokemonState.observeAsState()
    /*
    DisposableEffect helps the UI stay up-to-date with the latest
    information only when needed, avoiding unnecessary work when the UI is not in use.
     */
//    DisposableEffect(viewModel) {
//        viewModel.FetchPokemon(name ?: "")
//        onDispose { /* Dispose logic if needed */ }
//    }
        
        when{
            viewState?.isLoading == true ->{
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }
            viewState?.error != null ->{
                Text(text = "Error occured!! ${viewState!!.error}")
            }
            else ->{
                LazyColumn {
                    viewState?.let {
                        items(it.list){ PokemonDetail ->
                            PokemonLayout(pokemon = PokemonDetail,navigateBack,name)

                        }
                    }
                }
            }
        }
        
    
}

@Composable
fun PokemonLayout(pokemon:PokemonDetail,navigateBack:()->Unit,name: String?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(BorderStroke(8.dp, Color.Red)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "You had type this name : ${name}")
        Text(text = "Your pokemon name is ${pokemon.name}")
        Text(text = "Your pokemon weight is ${pokemon.weight}")
        Text(text = "Your pokemon height is ${pokemon.height}")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navigateBack() }) {
            Text(text = "Go Back")
        }

    }
}