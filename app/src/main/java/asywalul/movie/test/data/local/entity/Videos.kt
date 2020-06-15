package asywalul.movie.test.data.local.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Videos(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("key")
    val key: String? = null,

    @field:SerializedName("name")
    val name: String? = null


):Serializable