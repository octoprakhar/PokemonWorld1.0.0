package com.example.pokemonworld

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonworld.data.PokemonDetail
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    //To get user's input change this below line's mutable state to Mutable Live Data.
    //ChatGPT explanation ->
    /*
    In your MainViewModel, let's use MutableLiveData instead of MutableState to observe changes
     in the data. We will also expose a LiveData version of the state for the UI to observe.
     */
//    private val _pokemonState = mutableStateOf(PokemonState())
//    val pokemonState:State<PokemonState> = _pokemonState

    private val _pokemonState = MutableLiveData<PokemonState>()
    val pokemonState:LiveData<PokemonState> = _pokemonState

//    init {
//        FetchPokemon(pokemonName)
//    }

    fun FetchPokemon(name: String){
        viewModelScope.launch {
            try {
                val response = retrofitService.getPokemon(name)

                //In your MainViewModel, ensure that you use the viewModelScope properly.
//                _pokemonState.value = _pokemonState.value?.copy(
//                    isLoading = false,
//                    error = null,
//                    list = listOf(PokemonDetail(
//                        name = response.name,
//                        weight = response.weight,
//                        height = response.height
//                    ))
//                )
                _pokemonState.value = PokemonState(
                    isLoading = false,
                    error = null,
                    list = listOf(
                        PokemonDetail(
                            name = response.name,
                            weight = response.weight,
                            height = response.height
                        )
                    )
                )
            }catch (e:Exception){
//                _pokemonState.value = _pokemonState.value?.copy(
//                    isLoading = false,
//                    error = "Error Occured!!! ${e.message}",
//                    list = emptyList()
//                )

                _pokemonState.value = PokemonState(
                    isLoading = false,
                    error = "Error Occured!!! ${e.message}",
                    list = emptyList()
                )
            }
        }

    }

    data class PokemonState(
        val isLoading:Boolean = true
        ,
        val list: List<PokemonDetail> = emptyList(),
        val error: String? = null
    )

}