package asywalul.movie.test.model.api

import asywalul.movie.test.data.local.entity.Detail
import asywalul.movie.test.model.response.PopularResponse
import asywalul.movie.test.data.local.entity.ReviewsResponse
import asywalul.movie.test.model.response.DiscoverResponse
import asywalul.movie.test.model.response.GenresResponse
import asywalul.movie.test.model.response.UpcomingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/upcoming")
    fun getMovieUpComing(@Query("api_key") apiKey: String): Observable<UpcomingResponse>

    @GET("movie/popular")
    fun getMoviePopular(@Query("api_key") apiKey: String): Observable<PopularResponse>

    @GET("genre/movie/list")
    fun getMovieGenres(@Query("api_key") apiKey: String): Observable<GenresResponse>


    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") idMovie: String,
                        @Query("api_key") apiKey:String): Observable<ReviewsResponse>

    @GET("discover/movie")
    fun getMovieDiscover(@Query("page") page:Int,
                        @Query("with_genres") genreId: Int,
                        @Query("api_key") apiKey:String): Observable<DiscoverResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") apiKey: String,
                       @Query("api_key") idMovie:String): Observable<Detail>

}