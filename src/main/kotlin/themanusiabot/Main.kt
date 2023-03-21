package themanusiabot

import LocalHelper
import com.github.ygimenez.method.Pages
import com.github.ygimenez.model.PaginatorBuilder
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import org.slf4j.LoggerFactory
import themanusiabot.commands.Find
import themanusiabot.commands.Ping
import themanusiabot.core.Client
import themanusiabot.core.ExampleCommand

class Main {
    companion object {
        private val logger = LoggerFactory.getLogger(Main::class.java)
        var isDev = false

        @JvmStatic
        fun main(args: Array<String>) {
            if (LocalHelper.LocalHelper.getValue(LocalHelper.LocalHelper.DISCORD_API) == null && args.isEmpty())
                throw IllegalArgumentException("No arguments found")

            isDev = (LocalHelper.LocalHelper.getValue(LocalHelper.LocalHelper.DEV_MODE) ?: args[0]) == "true"
            val token: String = LocalHelper.LocalHelper.getValue(LocalHelper.LocalHelper.DISCORD_API) ?: args[1]
            logger.info("isDev: $isDev")

            if (token.isEmpty())
                throw IllegalArgumentException("Token is empty")

            val builder = JDABuilder.createDefault(token)
            builder.setActivity(Activity.listening("Ambatunat sad ver."))

            val jda = builder.build()

            Pages.activate(PaginatorBuilder.createSimplePaginator(jda))

            val testClient = Client(jda)
                .addCommand(ExampleCommand())
                .addCommand(Ping())
                .addCommand(Find())

            testClient.build()
        }
    }
}