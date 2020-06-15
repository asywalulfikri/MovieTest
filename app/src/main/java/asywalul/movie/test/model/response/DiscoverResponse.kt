package asywalul.movie.test.model.response

import asywalul.movie.test.data.local.entity.Discover
import com.google.gson.annotations.SerializedName

data class DiscoverResponse(


        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("results")
        val results: List<Discover>,

        @field:SerializedName("total_results")
        val totalResults: Int? = null
)