package asywalul.movie.test.model.repository

import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.data.local.source.LocalDataSourceImpl
import asywalul.movie.test.data.remote.source.RemoteDataSourceImpl
import asywalul.movie.test.intface.MovieRepository
import asywalul.movie.test.model.response.DiscoverResponse
import io.reactivex.Observable


class MovieRepositoryImpl(private val remoteImpl: RemoteDataSourceImpl, private val localImpl: LocalDataSourceImpl) : MovieRepository {

    override fun getMoviePopular(): Observable<List<Popular>> =
        Observable.concatArrayEager(localImpl.getAllMoviePopular(), remoteImpl.getMoviePopularFromApi())

    override fun getMovieUpComing(): Observable<List<UpComing>> =
        Observable.concatArrayEager(localImpl.getAllMovieUpComing(), remoteImpl.getMovieUpComingFromApi())

    override fun getMovieGenres(): Observable<List<Genres>> =
        Observable.concatArrayEager(localImpl.getAllMovieGenres(), remoteImpl.getMovieGenresFromApi())

    override fun getMovieReviews(idMovie: String): Observable<ReviewsResponse> =
        Observable.concatArrayEager(localImpl.getMovieReviews(idMovie), remoteImpl.getMovieReviewsFromApi(idMovie))

    override fun getMovieDetail(idMovie: String): Observable<Detail> =
        Observable.concatArrayEager(localImpl.getMovieDetail(idMovie), remoteImpl.getMovieDetailFromApi(idMovie))

    override fun getMovieDiscover(page: Int, genreId: Int): Observable<DiscoverResponse> =
        Observable.concatArray(remoteImpl.getMovieDiscoverFromApi(page, genreId))

}