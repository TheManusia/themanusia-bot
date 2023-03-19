package themanusiabot.network.tracemoe.response

import com.google.gson.annotations.SerializedName

data class Anilist(
    @SerializedName("id")
    val id: Int,
    @SerializedName("idMal")
    val idMal: Int,
    @SerializedName("title")
    val title: Title,
    @SerializedName("isAdult")
    val isAdult: Boolean,
)