package themanusiabot.core

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.interactions.components.buttons.Button
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu

class ExampleCommand : Command() {
    init {
        name = "halo"
        description = "Example command"
        options.add(OptionData(OptionType.USER, "user", "user to say hello to", false))
        options.add(OptionData(OptionType.STRING, "string", "string to say hello to", false))
    }

    override fun execute(event: SlashCommandInteractionEvent) {
        if (event.getOption("user") != null)
            event.reply("Halo bang <@${event.getOption("user")?.asUser?.id}>")
                .addActionRow(
                    Button.primary("halo:halo", "Halo!"),
                    Button.secondary("halo:mantap", "Mantap!"),
                )
                .queue()
        else if (event.getOption("string") != null)
            event.reply("Halo bang ${event.getOption("string")?.asString}")
                .addActionRow(
                    EntitySelectMenu.create("halo:orang", EntitySelectMenu.SelectTarget.USER).build()
                )
                .queue()
        else
            event.reply("Halo bang <@${event.user.id}>")
                .addActionRow(
                    StringSelectMenu.create("halo:makan")
                        .addOption("Nasi Goreng", "nasi goreng")
                        .addOption("Nasi Padang", "nasi padang")
                        .addOption("Nasi Uduk", "nasi uduk")
                        .addOption("Nasi Kuning", "nasi kuning")
                        .addOption("Nasi Kucing", "nasi kucing")
                        .build(),
                )
                .queue()
    }

    override fun executeButton(event: ButtonInteractionEvent, commandName: String) {
        when (commandName) {
            "halo" -> event.editMessage("Halo juga bang <@${event.user.id}>").queue()
            "mantap" -> event.editMessage("Mantap juga bang <@${event.user.id}>").queue()
        }
    }

    override fun executeStringDropdown(event: StringSelectInteractionEvent, commandName: String) {
        when (commandName) {
            "makan" -> event.editMessage("Makan ${event.values.first()} juga bang <@${event.user.id}>").queue()
        }
    }

    override fun executeEntityDropdown(event: EntitySelectInteractionEvent, commandName: String) {
        when (commandName) {
            "orang" -> {
                event.editMessage("Halo juga bang <@${event.mentions.users.first().id}>").queue()
            }
        }
    }
}