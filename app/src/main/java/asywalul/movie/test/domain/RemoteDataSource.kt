package asywalul.movie.test.domain

import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
import io.reactivex.Observable

interface RemoteDataSource {

    fun getMoviePopularFromApi(): Observable<List<Popular>>
    fun getMovieUpComingFromApi(): Observable<List<UpComing>>
    fun getMovieReviewsFromApi(idMovie:String):Observable<List<Reviews>>


}