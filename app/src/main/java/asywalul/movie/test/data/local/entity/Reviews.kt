package asywalul.movie.test.data.local.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class Reviews(

    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: String? =null,

    @ColumnInfo(name = "author")
    @field:SerializedName("author")
    val author: String? = null,

    @ColumnInfo(name = "content")
    @field:SerializedName("content")
    val content: String? = null,

    @ColumnInfo(name = "url")
    @field:SerializedName("url")
    val url: String? = null

)