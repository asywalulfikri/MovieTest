package asywalul.movie.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import asywalul.movie.test.data.local.dao.MovieDao
import asywalul.movie.test.data.local.entity.*

@Database(entities = [Popular::class,UpComing::class,ReviewsResponse::class,Detail::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class,ConvertersReviews::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}