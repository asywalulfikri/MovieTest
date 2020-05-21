package asywalul.movie.test.domain

import asywalul.movie.test.data.local.entity.Detail
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
import asywalul.movie.test.data.local.entity.ReviewsResponse
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalDataSource {

    fun saveMoviePopular(movies: List<Popular>): Completable
    fun saveMovieUpComing(movies: List<UpComing>): Completable
    fun saveMovieReviews(movies: ReviewsResponse):Completable
    fun saveMovieDetail(movies: Detail):Completable

    fun getAllMoviePopular(): Observable<List<Popular>>
    fun getAllMovieUpComing(): Observable<List<UpComing>>
    fun getMovieReviews(idMovie: String):Observable<ReviewsResponse>
    fun getMovieDetail(idMovie :String):Observable<Detail>


}