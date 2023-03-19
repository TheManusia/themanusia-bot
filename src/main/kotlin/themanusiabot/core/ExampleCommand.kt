package themanusiabot.core

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData

class ExampleCommand : Command() {
    init {
        name = "halo"
        description = "Example command"
        options.add(OptionData(OptionType.USER, "user", "user to say hello to", false))
    }

    override fun execute(event: SlashCommandInteractionEvent) {
        if (event.getOption("user") != null)
            event.reply("Halo bang <@${event.getOption("user")?.asUser?.id}>").queue()
        else
            event.reply("Halo bang <@${event.user.id}>").queue()
    }
}