package themanusiabot.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import themanusiabot.core.Command
import themanusiabot.network.tracemoe.TraceMoe

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
        val url: String

        if (event.getOption("image") != null) {
            url = event.getOption("image")!!.asAttachment.url
        } else if (event.getOption("url") != null) {
            url = event.getOption("url")!!.asString
        } else {
            event.reply("Please insert image or image url").queue()
            return
        }

        event.deferReply().queue()

        tracemoe.getSauce(url, event.hook)
    }
}