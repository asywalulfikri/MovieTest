package asywalul.movie.test.domain

import asywalul.movie.test.data.local.entity.Detail
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.UpComing
import asywalul.movie.test.data.local.entity.ReviewsResponse
import io.reactivex.Observable

interface MovieRepository {

    fun getMoviePopular(): Observable<List<Popular>>
    fun getMovieUpComing(): Observable<List<UpComing>>
    fun getMovieReviews(idMovie : String):Observable<ReviewsResponse>
    fun getMovieDetail(idMovie : String):Observable<Detail>

}