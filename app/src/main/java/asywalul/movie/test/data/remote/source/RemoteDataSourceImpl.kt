package asywalul.movie.test.data.remote.source

import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.data.local.source.LocalDataSourceImpl
import asywalul.movie.test.intface.RemoteDataSource
import asywalul.movie.test.intface.SchedulerProviders
import asywalul.movie.test.model.api.MovieApi
import asywalul.movie.test.model.response.DiscoverResponse
import asywalul.movie.test.utils.Constant
import io.reactivex.Observable

class RemoteDataSourceImpl(private val api: MovieApi,
                           private val localImpl: LocalDataSourceImpl,
                           private val scheduler: SchedulerProviders
) : RemoteDataSource {

    override fun getMoviePopularFromApi(): Observable<List<Popular>> =
        api.getMoviePopular(Constant.KEY.key)
            .map { movieResponse ->
                localImpl.saveMoviePopular(movieResponse.results).subscribe()
                movieResponse.results
            }
            .subscribeOn(scheduler.io())

    override fun getMovieUpComingFromApi(): Observable<List<UpComing>> =
        api.getMovieUpComing(Constant.KEY.key)
            .map { movieResponse ->
                localImpl.saveMovieUpComing(movieResponse.results).subscribe()
                movieResponse.results
            }
            .subscribeOn(scheduler.io())

    override fun getMovieDiscoverFromApi(page : Int , genreId : Int): Observable<DiscoverResponse> =
        api.getMovieDiscover(page,genreId,Constant.KEY.key)

    override fun getMovieGenresFromApi(): Observable<List<Genres>> =
        api.getMovieGenres(Constant.KEY.key)
            .map { movieResponse ->
                localImpl.saveMovieGenres(movieResponse.results).subscribe()
                movieResponse.results
            }
            .subscribeOn(scheduler.io())


    override fun getMovieReviewsFromApi(idMovie:String): Observable<ReviewsResponse> =
        api.getMovieReviews(idMovie,Constant.KEY.key)
            .map { movieResponse ->
                localImpl.saveMovieReviews(movieResponse).subscribe()
                movieResponse

            }
            .subscribeOn(scheduler.io())


    override fun getMovieDetailFromApi(idMovie: String): Observable<Detail> =
        api.getMovieDetail(idMovie,Constant.KEY.key)
            .map { movieResponse ->
                localImpl.saveMovieDetail(movieResponse).subscribe()
                movieResponse

            }
            .subscribeOn(scheduler.io())


}