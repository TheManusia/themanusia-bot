package themanusiabot.core

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.slf4j.LoggerFactory

abstract class Command {
    var name = ""
    var description = "No Description"
    val options: MutableList<OptionData> = ArrayList()

    private val logger = LoggerFactory.getLogger(Command::class.java)

    protected abstract fun execute(event: SlashCommandInteractionEvent)

    fun run(event: SlashCommandInteractionEvent) {
        logger.debug("Command run")

        if (event.guild == null) {
            event.reply("This command only can be used in server").setEphemeral(true).queue()
            return
        }

//        if (event.member?.hasPermission(permission)!!) {
//            event.reply("You don't have permission to use this command").setEphemeral(true).queue()
//            return
//        }

        execute(event)
    }
}