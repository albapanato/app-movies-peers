interface TmdbApiService {
    @GET("movie/popular")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String): MovieResponse
}