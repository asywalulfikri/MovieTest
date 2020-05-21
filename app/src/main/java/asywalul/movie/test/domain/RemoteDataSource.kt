package asywalul.movie.test.domain

import asywalul.movie.test.data.local.entity.Detail
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
import asywalul.movie.test.data.local.entity.ReviewsResponse
import io.reactivex.Observable

interface RemoteDataSource {

    fun getMoviePopularFromApi(): Observable<List<Popular>>
    fun getMovieUpComingFromApi(): Observable<List<UpComing>>
    fun getMovieReviewsFromApi(idMovie:String):Observable<ReviewsResponse>
    fun getMovieDetailFromApi(idMovie:String):Observable<Detail>

}