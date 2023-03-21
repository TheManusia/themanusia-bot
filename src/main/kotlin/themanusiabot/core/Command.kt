package themanusiabot.core

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.slf4j.LoggerFactory

abstract class Command {
    var name = ""
    var description = "No Description"
    val options: MutableList<OptionData> = ArrayList()

    private val logger = LoggerFactory.getLogger(Command::class.java)

    protected abstract fun execute(event: SlashCommandInteractionEvent)

    open fun executeButton(event: ButtonInteractionEvent, commandName:String) {
        logger.info("Button $name:$commandName not implemented")
    }

    fun run(event: SlashCommandInteractionEvent) {
        logger.info("Command $name run")

        execute(event)
    }

    fun runButton(event: ButtonInteractionEvent, commandName: String) {
        logger.info("Button $name:$commandName run")

        executeButton(event, commandName)
    }
}