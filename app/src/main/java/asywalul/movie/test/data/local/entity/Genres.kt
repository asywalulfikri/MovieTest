package asywalul.movie.test.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import asywalul.movie.test.utils.Constant
import com.google.gson.annotations.SerializedName
import retrofit2.Converter
import java.io.Serializable


@Entity(tableName = Constant.TABLE.tbl_genres)
data class Genres(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Long? = null,
    @field:SerializedName("name")
    val name: String? = null

):Serializable