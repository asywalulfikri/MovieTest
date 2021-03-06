package asywalul.movie.test.intface

import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.model.response.DiscoverResponse
import asywalul.movie.test.model.response.VideoResponse
import io.reactivex.Observable

interface MovieRepository {

    fun getMoviePopular(): Observable<List<Popular>>
    fun getMovieUpComing(): Observable<List<UpComing>>
    fun getMovieGenres(): Observable<List<Genres>>
    fun getMovieReviews(movieId : String):Observable<ReviewsResponse>
    fun getMovieDetail(movieId : String):Observable<Detail>
    fun getMovieDiscover(page : Int , genreId : Int):Observable<DiscoverResponse>
    fun getMovieVideo(movieId : String):Observable<VideoResponse>
}