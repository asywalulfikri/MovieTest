package asywalul.movie.test.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import asywalul.movie.test.utils.Constant
import com.google.gson.annotations.SerializedName


@Entity(tableName = Constant.TABLE.tbl_review)
data class Reviews(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Long? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    @field:SerializedName("url")
    val url: String? = null


)