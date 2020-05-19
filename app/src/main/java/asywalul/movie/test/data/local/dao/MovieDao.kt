package asywalul.movie.test.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
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
    fun insertMovieReview(movie: List<Reviews>): Completable


    //Get
    @Query("SELECT * FROM popular")
    fun getMoviePopular(): Observable<List<Popular>>

    @Query("SELECT * FROM upcoming")
    fun getMovieUpComing(): Observable<List<UpComing>>

    @Query("SELECT * FROM reviews")
    fun getMovieReviews(): Observable<List<Reviews>>

}