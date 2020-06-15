package asywalul.movie.test.model.response

import asywalul.movie.test.data.local.entity.Genres
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenresResponse(


        @field:SerializedName("genres")
        val results: List<Genres>
): Serializable