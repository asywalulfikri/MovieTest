package asywalul.movie.test.model.repository

import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.data.local.source.LocalDataSourceImpl
import asywalul.movie.test.data.remote.source.RemoteDataSourceImpl
import asywalul.movie.test.intface.MovieRepository
import asywalul.movie.test.model.response.DiscoverResponse
import asywalul.movie.test.model.response.VideoResponse
import io.reactivex.Observable


class MovieRepositoryImpl(private val remoteImpl: RemoteDataSourceImpl, private val localImpl: LocalDataSourceImpl) : MovieRepository {

    override fun getMoviePopular(): Observable<List<Popular>> =
        Observable.concatArrayEager(localImpl.getAllMoviePopular(), remoteImpl.getMoviePopularFromApi())

    override fun getMovieUpComing(): Observable<List<UpComing>> =
        Observable.concatArrayEager(localImpl.getAllMovieUpComing(), remoteImpl.getMovieUpComingFromApi())

    override fun getMovieGenres(): Observable<List<Genres>> =
        Observable.concatArrayEager(localImpl.getAllMovieGenres(), remoteImpl.getMovieGenresFromApi())

    override fun getMovieReviews(movieId: String): Observable<ReviewsResponse> =
        Observable.concatArrayEager(localImpl.getMovieReviews(movieId), remoteImpl.getMovieReviewsFromApi(movieId))

    override fun getMovieDetail(movieId: String): Observable<Detail> =
        Observable.concatArrayEager(localImpl.getMovieDetail(movieId), remoteImpl.getMovieDetailFromApi(movieId))

    override fun getMovieDiscover(page: Int, genreId: Int): Observable<DiscoverResponse> =
        Observable.concatArray(remoteImpl.getMovieDiscoverFromApi(page, genreId))

    override fun getMovieVideo(movieId : String): Observable<VideoResponse> =
        Observable.concatArray(remoteImpl.getMovieVideoFromApi(movieId))

}