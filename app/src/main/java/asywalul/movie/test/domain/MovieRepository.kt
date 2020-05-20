package asywalul.movie.test.domain

import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
import io.reactivex.Observable

interface MovieRepository {

    fun getMoviePopular(): Observable<List<Popular>>
    fun getMovieUpComing(): Observable<List<UpComing>>
    fun getMovieReviews(idMovie : String):Observable<List<Reviews>>

}