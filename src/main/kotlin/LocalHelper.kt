import java.io.File
import java.io.FileInputStream
import java.util.*

class LocalHelper {
    object LocalHelper {

        const val DISCORD_API = "DISCORD_API"
        const val DEV_MODE = "DEV_MODE"

        private val properties by lazy {
            Properties().apply {
                if (File(".env").exists()) {
                    load(FileInputStream(File(".env")))
                } else if (File("local.properties").exists()) {
                    load(FileInputStream(File("local.properties")))
                } else {
                    throw IllegalArgumentException("No local.properties or .env file found")
                }
            }
        }

        fun getValue(key: String): String {
            return properties.getProperty(key)
        }
    }
}