package asywalul.movie.test.model.repository

import asywalul.movie.test.data.local.source.LocalDataSourceImpl
import asywalul.movie.test.data.remote.source.RemoteDataSourceImpl
import asywalul.movie.test.domain.MovieRepository
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
import io.reactivex.Observable


class MovieRepositoryImpl(private val remoteImpl: RemoteDataSourceImpl, private val localImpl: LocalDataSourceImpl) : MovieRepository {


    override fun getMoviePopular(): Observable<List<Popular>> =
        Observable.concatArrayEager(localImpl.getAllMoviePopular(), remoteImpl.getMoviePopularFromApi())

    override fun getMovieUpComing(): Observable<List<UpComing>> =
        Observable.concatArrayEager(localImpl.getAllMovieUpComing(), remoteImpl.getMovieUpComingFromApi())

    override fun getMovieReviews(idMovie :String): Observable<List<Reviews>> =
        Observable.concatArrayEager(localImpl.getMovieReviews(), remoteImpl.getMovieReviewsFromApi(idMovie))

}