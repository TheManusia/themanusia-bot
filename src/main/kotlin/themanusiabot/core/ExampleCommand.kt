package themanusiabot.core

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.interactions.components.buttons.Button

class ExampleCommand : Command() {
    init {
        name = "halo"
        description = "Example command"
        options.add(OptionData(OptionType.USER, "user", "user to say hello to", false))
    }

    override fun execute(event: SlashCommandInteractionEvent) {
        if (event.getOption("user") != null)
            event.reply("Halo bang <@${event.getOption("user")?.asUser?.id}>")
                .addActionRow(
                    Button.primary("halo:halo", "Halo!"),
                    Button.secondary("halo:mantap", "Mantap!"),
                )
                .queue()
        else
            event.reply("Halo bang <@${event.user.id}>")
                .addActionRow(
                    Button.primary("halo:halo", "Halo!"),
                    Button.secondary("halo:mantap", "Mantap!"),
                )
                .queue()
    }

    override fun executeButton(event: ButtonInteractionEvent, commandName: String) {
        when (commandName) {
            "halo" -> event.editMessage("Halo juga bang <@${event.user.id}>").queue()
            "mantap" -> event.editMessage("Mantap juga bang <@${event.user.id}>").queue()
        }
    }
}