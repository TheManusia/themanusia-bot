package themanusiabot.core

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import themanusiabot.Main

class Client(jda: JDA) : ListenerAdapter() {
    private val jdaClient: JDA = jda

    private var built = false

    private val commands: ArrayList<Command> = ArrayList()

    private val logger: Logger = LoggerFactory.getLogger(Client::class.java)

    fun addCommand(command: Command?): Client {
        commands.add(command!!)
        return this
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        logger.info("onMessageReceived run")
        val name = event.name
        if (!event.user.isBot && event.interaction.isFromGuild) {
            checkCommand(name)?.run(event)
        }
    }

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        logger.info("onButtonInteraction run")
        val name = event.componentId
        if (!event.user.isBot && event.interaction.isFromGuild) {
            val id = name.split(":")
            checkButton(id[0])?.runButton(event, id[1])
        }
    }

    private fun checkCommand(commandName: String): Command? {
        val name = commandName.trim { it <= ' ' }
        for (command in commands) {
            if (command.name == name) return command
        }
        return null
    }

    private fun checkButton(buttonName: String): Command? {
        for (command in commands) {
            if (command.name == buttonName) return command
        }
        return null
    }

    fun build() {
        if (built)
            throw IllegalStateException("Client already built")

        built = true
        jdaClient.addEventListener(this)

        jdaClient.updateCommands().addCommands(
            commands.map { command ->
                Commands.slash(command.name, command.description)
                    .addOptions(command.options)
                    .setGuildOnly(Main.isDev)
            }
        ).queue()
    }
}