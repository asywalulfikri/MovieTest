package asywalul.movie.test.intface

import asywalul.movie.test.data.local.entity.*
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalDataSource {

    fun saveMoviePopular(movies: List<Popular>): Completable
    fun saveMovieUpComing(movies: List<UpComing>): Completable
    fun saveMovieGenres(movies: List<Genres>): Completable
    fun saveMovieReviews(movies: ReviewsResponse):Completable
    fun saveMovieDetail(movies: Detail):Completable

    fun getAllMoviePopular(): Observable<List<Popular>>
    fun getAllMovieUpComing(): Observable<List<UpComing>>
    fun getAllMovieGenres(): Observable<List<Genres>>
    fun getMovieReviews(idMovie: String):Observable<ReviewsResponse>
    fun getMovieDetail(idMovie :String):Observable<Detail>


}