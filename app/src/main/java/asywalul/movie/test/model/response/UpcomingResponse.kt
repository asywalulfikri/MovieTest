package asywalul.movie.test.model.response

import asywalul.movie.test.data.local.entity.UpComing
import com.google.gson.annotations.SerializedName

data class UpcomingResponse(

        @field:SerializedName("dates")
        val dates: Dates? = null,

        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("results")
        val results: List<UpComing>,

        @field:SerializedName("total_results")
        val totalResults: Int? = null
)