package asywalul.movie.test.intface

import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.model.response.DiscoverResponse
import asywalul.movie.test.model.response.VideoResponse
import io.reactivex.Observable

interface RemoteDataSource {

    fun getMoviePopularFromApi(): Observable<List<Popular>>
    fun getMovieUpComingFromApi(): Observable<List<UpComing>>
    fun getMovieDiscoverFromApi(page :Int,genreId :Int): Observable<DiscoverResponse>
    fun getMovieVideoFromApi(movieId : String): Observable<VideoResponse>
    fun getMovieGenresFromApi(): Observable<List<Genres>>
    fun getMovieReviewsFromApi(movieId:String):Observable<ReviewsResponse>
    fun getMovieDetailFromApi(movieId:String):Observable<Detail>

}