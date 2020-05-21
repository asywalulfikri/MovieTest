package asywalul.movie.test.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import asywalul.movie.test.utils.Constant
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constant.TABLE.tbl_review)
data class ReviewsResponse(

        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = false)
        @field:SerializedName("id")
        var id: Long? = null,

        @ColumnInfo(name = "page")
        @field:SerializedName("page")
        val page: Int? = null,

        @ColumnInfo(name = "total_pages")
        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @ColumnInfo(name = "results")
        @field:SerializedName("results")
        @TypeConverters(ConvertersReviews::class)
        val results: List<Reviews>,

        @ColumnInfo(name = "total_results")
        @field:SerializedName("total_results")
        val totalResults: Int? = null
)