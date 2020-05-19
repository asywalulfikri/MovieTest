package asywalul.movie.test.data.remote.source


import asywalul.movie.test.data.local.source.LocalDataSourceImpl
import asywalul.movie.test.domain.RemoteDataSource
import asywalul.movie.test.domain.SchedulerProviders
import asywalul.movie.test.model.api.MovieApi
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.UpComing
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


}