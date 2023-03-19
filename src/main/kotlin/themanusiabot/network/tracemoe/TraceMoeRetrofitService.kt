package themanusiabot.network.tracemoe

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TraceMoeRetrofitService {
    val service: TraceMoeService
        get() {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(TraceMoeService::class.java)
        }

    companion object {
        private const val URL = "https://api.trace.moe/"
    }
}