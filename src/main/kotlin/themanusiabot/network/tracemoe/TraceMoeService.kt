package themanusiabot.network.tracemoe

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import themanusiabot.network.tracemoe.response.Response

interface TraceMoeService {

    @GET("search?anilistInfo")
    fun getSauce(@Query("url") url: String): Call<Response>
}