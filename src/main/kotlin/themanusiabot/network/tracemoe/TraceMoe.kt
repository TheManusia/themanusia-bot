package themanusiabot.network.tracemoe

import dev.minn.jda.ktx.messages.EmbedBuilder
import net.dv8tion.jda.api.interactions.InteractionHook
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import themanusiabot.network.tracemoe.response.Response

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
     * @param hook InteractionHook
     */
    fun getSauce(image: String, hook: InteractionHook) {
        logger.info(image)
        traceMoeService.getSauce(image).enqueue(object : Callback<Response?> {
            override fun onResponse(call: Call<Response?>, response: retrofit2.Response<Response?>) {
                try {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val sauces = response.body()!!.sauces
                            if (sauces.isNotEmpty()) {
                                val sauce = sauces[0]
                                hook.sendMessageEmbeds(
                                    EmbedBuilder {
                                        this.title = sauce.anilist.title.romajiTitle
                                        this.url = "https://myanimelist.net/anime/${sauce.anilist.idMal}"
                                        this.image = sauce.imageUrl
                                        this.color = 0XF7EFC6
                                        this.field {
                                            this.name = "Similarity"
                                            this.value = "${(sauce.similarity * 100).toInt()}%"
                                            this.inline = true
                                        }
                                        this.field {
                                            this.name = "Episode"
                                            this.value = "${sauce.episode}"
                                            this.inline = true
                                        }
                                    }.build()
                                ).queue()
                            } else {
                                hook.sendMessage("Sauce not found").queue()
                            }
                        } else {
                            hook.sendMessage("Sauce not found").queue()
                        }
                    } else {
                        hook.sendMessage("An error occurred").queue()
                        logger.error(response.message())
                    }
                } catch (e: Exception) {
                    hook.sendMessage("An error occurred").queue()
                    logger.error(e.message, e)
                }
            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {
                hook.sendMessage("An error occurred").queue()
            }
        })
    }
}