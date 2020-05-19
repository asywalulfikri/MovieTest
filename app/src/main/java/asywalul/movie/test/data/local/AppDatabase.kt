package asywalul.movie.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import asywalul.movie.test.data.local.dao.MovieDao
import asywalul.movie.test.data.local.entity.Converters
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing

@Database(entities = [Popular::class,UpComing::class,Reviews::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}