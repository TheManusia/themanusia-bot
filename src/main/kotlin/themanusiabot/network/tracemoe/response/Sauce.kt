package themanusiabot.network.tracemoe.response

import com.google.gson.annotations.SerializedName

data class Sauce(
    @SerializedName("anilist")
    val anilist: Anilist,
    @SerializedName("filename")
    val filename: String,
    @SerializedName("episode")
    val episode: Int,
    @SerializedName("similarity")
    val similarity: Double,
    @SerializedName("image")
    val imageUrl: String,
) {

}
