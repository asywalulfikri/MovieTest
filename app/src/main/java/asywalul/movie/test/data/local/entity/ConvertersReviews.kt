package asywalul.movie.test.data.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import org.koin.core.KoinComponent

class ConvertersReviews : KoinComponent {

    @TypeConverter
    fun listToJson(value: List<Reviews>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Reviews>::class.java).toList()
}