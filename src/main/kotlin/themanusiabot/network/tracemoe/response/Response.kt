package themanusiabot.network.tracemoe.response

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("result")
    val sauces: MutableList<Sauce>,
)