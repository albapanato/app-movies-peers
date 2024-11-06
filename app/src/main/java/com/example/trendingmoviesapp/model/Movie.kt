data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val overview: String
)

data class MovieResponse(
    val results: List<Movie>
)