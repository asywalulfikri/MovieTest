package asywalul.movie.test.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import asywalul.movie.test.data.local.entity.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MovieDao {

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviePopular(movie: List<Popular>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieUpComing(movie: List<UpComing>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenres(movie: List<Genres>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieReview(movie: ReviewsResponse): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movie: Detail): Completable

    //Get
    @Query("SELECT * FROM popular")
    fun getMoviePopular(): Observable<List<Popular>>

    @Query("SELECT * FROM upcoming")
    fun getMovieUpComing(): Observable<List<UpComing>>

    @Query("SELECT * FROM genres")
    fun getMovieGenres(): Observable<List<Genres>>

    @Query("SELECT * FROM detail where id = :idMovie")
    fun getMovieDetail(idMovie:String): Observable<Detail>

    @Query("SELECT * FROM reviews where id = :idMovie")
    fun getMovieReviews(idMovie: String): Observable<ReviewsResponse>


}