package themanusiabot.commands

import com.github.ygimenez.method.Pages
import com.github.ygimenez.model.InteractPage
import com.github.ygimenez.model.Page
import dev.minn.jda.ktx.messages.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import themanusiabot.core.Command
import themanusiabot.network.tracemoe.TraceMoe
import themanusiabot.network.tracemoe.response.Sauce

class Find : Command() {

    var tracemoe: TraceMoe

    init {
        name = "find"
        description = "Find anime from image"
        options.add(OptionData(OptionType.ATTACHMENT, "image", "insert image you want to search", false))
        options.add(OptionData(OptionType.STRING, "url", "insert image url you want to search", false))

        tracemoe = TraceMoe()
    }

    override fun execute(event: SlashCommandInteractionEvent) {
        val url = if (event.getOption("image") != null) {
            event.getOption("image")!!.asAttachment.url
        } else if (event.getOption("url") != null) {
            event.getOption("url")!!.asString
        } else {
            event.reply("Please insert image or image url").queue()
            return
        }

        event.deferReply().queue()
        val hook = event.hook

        tracemoe.getSauce(url, sauces = { sauces, message ->
            if (sauces.isNotEmpty()) {
                val sauceList: List<Page> = sauces.map {
                    InteractPage(buildEmbed(it))
                }
                hook.sendMessageEmbeds(sauceList.first().content as MessageEmbed)
                    .queue {
                        Pages.paginate(it, sauceList, true)
                    }
            } else {
                hook.sendMessage(message).queue()
            }
        })
    }

    private fun buildEmbed(sauce: Sauce): MessageEmbed {
        return EmbedBuilder {
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
    }

}