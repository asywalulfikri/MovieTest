package asywalul.movie.test.data.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.core.KoinComponent
import java.util.*

class Converters : KoinComponent {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType =
            object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromResultListToJson(weather: List<Popular>): String {
        return Gson().toJson(weather)
    }

    @TypeConverter
    fun fromJsonToResultList(jsonWeatherList: String): List<Popular> {
        val weatherListType = object : TypeToken<List<Popular>>() {}.type
        return Gson().fromJson(jsonWeatherList, weatherListType)
    }

    companion object {
        @JvmStatic
        @TypeConverter
        fun fromStringToList(data: String): List<String> {
            val listType =
                object : TypeToken<List<String?>?>() {}.type
            return Gson().fromJson(data, listType)
        }

        @JvmStatic
        @TypeConverter
        fun listToString(data: List<String>): String {
            return Gson().toJson(data)
        }
    }
}