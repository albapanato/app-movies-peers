import com.example.trendingmoviesapp.model.Actor
import com.example.trendingmoviesapp.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse( // Esta clase es la que va a mapear la respuesta completa que recibes de la API. Las propiedades page, total_pages, total_results son todas relacionadas con la paginación.
    val page: Int,
    @SerialName("results") val movies: List<Movie>,
    @SerialName("actors") val actor: List<Actor>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
) {

}
