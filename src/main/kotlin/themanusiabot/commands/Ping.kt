package themanusiabot.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import themanusiabot.core.Command

class Ping: Command() {
    init {
        name = "ping"
        description = "Ping command"
    }

    override fun execute(event: SlashCommandInteractionEvent) {
        event.reply("Pong!").queue()
    }
}