class MovieViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TmdbApiService::class.java)

    suspend fun getTrendingMovies(apiKey: String) {
        val response = service.getTrendingMovies(apiKey)
        _movies.value = response.results
    }
}