package asywalul.movie.test.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import asywalul.movie.test.utils.Constant
import com.google.gson.annotations.SerializedName
import retrofit2.Converter
import java.io.Serializable

@Entity(tableName = Constant.TABLE.tbl_upcoming)
data class UpComing(

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("video")
    val video: Boolean? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("genre_ids")
    @TypeConverters(Converter::class)
    val genreIds: List<String>,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Float? = null,

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Long? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("vote_count")
    val voteCount: Long? = null

):Serializable