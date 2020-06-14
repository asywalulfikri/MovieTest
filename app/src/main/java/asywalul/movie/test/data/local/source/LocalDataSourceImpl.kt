package asywalul.movie.test.data.local.source

import asywalul.movie.test.data.local.dao.MovieDao
import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.intface.LocalDataSource
import asywalul.movie.test.intface.SchedulerProviders
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

    override fun getAllMovieGenres(): Observable<List<Genres>> =
        dao.getMovieGenres()
            .subscribeOn(scheduler.computation())
            .filter {
                it.isNotEmpty()
            }

    override fun getMovieReviews(idMovie: String): Observable<ReviewsResponse> =
        dao.getMovieReviews(idMovie)
            .subscribeOn(scheduler.computation())

    override fun getMovieDetail(idMovie :String): Observable<Detail> =
        dao.getMovieDetail(idMovie)
            .subscribeOn(scheduler.computation())


    override fun saveMoviePopular(movies: List<Popular>): Completable {
        return dao.insertMoviePopular(movies)
            .subscribeOn(scheduler.computation())
    }

    override fun saveMovieGenres(movies: List<Genres>): Completable {
        return dao.insertMovieGenres(movies)
            .subscribeOn(scheduler.computation())
    }

    override fun saveMovieUpComing(movies: List<UpComing>): Completable {
        return dao.insertMovieUpComing(movies)
            .subscribeOn(scheduler.computation())
    }

    override fun saveMovieReviews(movies: ReviewsResponse): Completable {
        return dao.insertMovieReview(movies)
            .subscribeOn(scheduler.computation())
    }

    override fun saveMovieDetail(movies: Detail): Completable {
        return dao.insertMovieDetail(movies)
            .subscribeOn(scheduler.computation())
    }


}

