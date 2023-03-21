package themanusiabot.network.tracemoe

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import themanusiabot.network.tracemoe.response.Response
import themanusiabot.network.tracemoe.response.Sauce

class TraceMoe {
    private var traceMoeService: TraceMoeService
    private val logger: Logger = LoggerFactory.getLogger(TraceMoe::class.java)

    init {
        val retrofitTraceMoeService = TraceMoeRetrofitService()
        traceMoeService = retrofitTraceMoeService.service
    }

    /**
     * Get image scene sauce from TraceMoe API
     *
     * @param image Image URL
     * @param sauces Callback function
     */
    fun getSauce(image: String, sauces: (input: List<Sauce>, message: String) -> Unit) {
        logger.info(image)
        traceMoeService.getSauce(image).enqueue(object : Callback<Response?> {
            override fun onResponse(call: Call<Response?>, response: retrofit2.Response<Response?>) {
                try {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            sauces(response.body()!!.sauces, "")
                        } else {
                            sauces(emptyList(), "No result found")
                            logger.error(response.message())
                        }
                    } else {
                        sauces(emptyList(), "No result found")
                        logger.error(response.message())
                    }
                } catch (e: Exception) {
                    sauces(emptyList(), "No result found")
                    logger.error(e.message, e)
                }
            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {
                sauces(emptyList(), "No result found")
                logger.error(t.message)
            }
        })
    }
}