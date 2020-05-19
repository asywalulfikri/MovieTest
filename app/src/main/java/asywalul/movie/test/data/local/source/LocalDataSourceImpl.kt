package asywalul.movie.test.data.local.source

import asywalul.movie.test.data.local.dao.MovieDao
import asywalul.movie.test.domain.LocalDataSource
import asywalul.movie.test.domain.SchedulerProviders
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
import io.reactivex.Completable
import io.reactivex.Observable

class LocalDataSourceImpl(private val dao: MovieDao, private val scheduler: SchedulerProviders) : LocalDataSource {

    override fun getAllMoviePopular(): Observable<List<Popular>> =
        dao.getMoviePopular()
            .subscribeOn(scheduler.computation())
            .filter {
                it.isNotEmpty()
            }

    override fun getAllMovieUpComing(): Observable<List<UpComing>> =
        dao.getMovieUpComing()
            .subscribeOn(scheduler.computation())
            .filter {
                it.isNotEmpty()
            }

    override fun getMovieReviews(): Observable<List<Reviews>> =
        dao.getMovieReviews()
            .subscribeOn(scheduler.computation())
            .filter {
                it.isNotEmpty()
            }



    override fun saveMoviePopular(movies: List<Popular>): Completable {
        return dao.insertMoviePopular(movies)
            .subscribeOn(scheduler.computation())
    }


    override fun saveMovieUpComing(movies: List<UpComing>): Completable {
        return dao.insertMovieUpComing(movies)
            .subscribeOn(scheduler.computation())
    }

    override fun saveMovieReviews(movies: List<Reviews>): Completable {
        return dao.insertMovieReview(movies)
            .subscribeOn(scheduler.computation())
    }


}

