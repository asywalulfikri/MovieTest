package asywalul.movie.test.model.response

import asywalul.movie.test.data.local.entity.Popular
import com.google.gson.annotations.SerializedName

data class PopularResponse(

        @field:SerializedName("dates")
        val dates: Dates? = null,

        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("results")
        val results: List<Popular>,

        @field:SerializedName("total_results")
        val totalResults: Int? = null
)