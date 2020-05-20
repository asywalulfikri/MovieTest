package asywalul.movie.test.model.api

import asywalul.movie.test.model.response.PopularResponse
import asywalul.movie.test.model.response.ReviewsResponse
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

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") apiKey: String,
                        @Query("api_key")idMovie:String): Observable<ReviewsResponse>

}