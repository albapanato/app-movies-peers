package com.example.trendingmoviesapp.viewmodel

import MovieResponse
import TmdbApiService
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingmoviesapp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppViewModel : ViewModel() {

    var query by mutableStateOf("") // Query de búsqueda para el servidor

    // Estado de la API Key usando StateFlow
    private val _apiKey = MutableStateFlow<String?>(null)
    val apiKey: StateFlow<String?> = _apiKey.asStateFlow()

    // Función para actualizar el valor de la API Key
    fun setApiKey(key: String) {
        _apiKey.value = key
    }
    // Lista de películas que se puede modificar
    var moviesList = mutableStateListOf<Movie>()
        private set

    // Índice de la película seleccionada
    var selectedMovieIndex by mutableStateOf(0)

    // Función para limpiar la lista de películas
    fun clearMoviesList() {
        moviesList.clear()
    }

    // Función para añadir todas las películas a la lista
    fun moviesListAddAll(moviesList: List<Movie>) {
        this.moviesList.addAll(moviesList)
    }

    private val apiService: TmdbApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(TmdbApiService::class.java)
    }

    val popularPersons = mutableListOf<MovieResponse>()

    fun fetchPopularPersons() {
        viewModelScope.launch {
            try {
                val response = apiService.getPopularPersons()
                if (response.isSuccessful) {
                    popularPersons.clear()
                    popularPersons.addAll((response.body()?.movies ?: emptyList()) as Collection<MovieResponse>)
                    Log.d("AppViewModel", "Fetched ${popularPersons.size} persons.")
                } else {
                    Log.e("AppViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AppViewModel", "Exception fetching popular persons", e)
            }
        }
    }
}

