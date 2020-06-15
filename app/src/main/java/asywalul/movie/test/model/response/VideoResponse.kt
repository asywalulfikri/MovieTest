package asywalul.movie.test.model.response

import asywalul.movie.test.data.local.entity.Videos
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoResponse(

        @field:SerializedName("results")
        val results: List<Videos>
): Serializable