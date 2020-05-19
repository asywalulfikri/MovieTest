package asywalul.movie.test.domain

import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalDataSource {

    fun saveMoviePopular(movies: List<Popular>): Completable
    fun saveMovieUpComing(movies: List<UpComing>): Completable
    fun saveMovieReviews(movies: List<Reviews>):Completable

    fun getAllMoviePopular(): Observable<List<Popular>>
    fun getAllMovieUpComing(): Observable<List<UpComing>>
    fun getMovieReviews():Observable<List<Reviews>>

}