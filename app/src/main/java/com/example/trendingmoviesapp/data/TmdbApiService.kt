import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TmdbApiService {
    @Headers(
        "Authorization: Bearer b8cc87f9398a183bd5c3be41730bd93a",
        "accept: application/json"
    )
    @GET("person/popular")
    suspend fun getPopularPersons(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>
}