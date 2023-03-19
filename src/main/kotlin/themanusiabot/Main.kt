package themanusiabot

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import themanusiabot.commands.Find
import themanusiabot.commands.Ping
import themanusiabot.core.Client
import themanusiabot.core.ExampleCommand

class Main {
    companion object {
        var isDev: Boolean = false

        @JvmStatic
        fun main(args: Array<String>) {
            isDev = args.isNotEmpty() && args[0] == "dev"
            println("isDev: $isDev")
            val token = if (isDev) args[1] else LocalHelper.LocalHelper.DISCORD_API
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