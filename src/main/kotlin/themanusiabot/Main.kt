package themanusiabot

import LocalHelper
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import themanusiabot.commands.Find
import themanusiabot.commands.Ping
import themanusiabot.core.Client
import themanusiabot.core.ExampleCommand

class Main {
    companion object {
        private val localHelper = LocalHelper.LocalHelper
        var isDev = false

        @JvmStatic
        fun main(args: Array<String>) {
            val token: String
            if (localHelper.isPropertiesLoaded()) {
                isDev = localHelper.getValue(localHelper.DEV_MODE) == "true"
                token = localHelper.getValue(localHelper.DISCORD_API)
            } else {
                token = args[1]
                isDev = args[0] == "true"
            }
            println("isDev: $isDev")
            if (token.isEmpty())
                throw IllegalArgumentException("Token is empty")

            val builder = JDABuilder.createDefault(token)
            builder.setActivity(Activity.listening("Ambatunat sad ver."))

            val testClient = Client(builder.build())
                .addCommand(ExampleCommand())
                .addCommand(Ping())
                .addCommand(Find())

            testClient.build()
        }
    }
}