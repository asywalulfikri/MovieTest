package asywalul.movie.test.intface

import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.model.response.DiscoverResponse
import io.reactivex.Observable

interface RemoteDataSource {

    fun getMoviePopularFromApi(): Observable<List<Popular>>
    fun getMovieUpComingFromApi(): Observable<List<UpComing>>
    fun getMovieDiscoverFromApi(page :Int,genreId :Int): Observable<DiscoverResponse>
    fun getMovieGenresFromApi(): Observable<List<Genres>>
    fun getMovieReviewsFromApi(idMovie:String):Observable<ReviewsResponse>
    fun getMovieDetailFromApi(idMovie:String):Observable<Detail>

}