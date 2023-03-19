package themanusiabot.network.tracemoe.response

import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("native")
    val nativeTitle: String,
    @SerializedName("romaji")
    val romajiTitle: String,
    @SerializedName("english")
    val englishTitle: String,
)