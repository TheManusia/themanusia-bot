import java.io.File
import java.io.FileInputStream
import java.util.*

class LocalHelper {
    object LocalHelper {

        const val DISCORD_API = "DISCORD_API"

        private val properties by lazy {
            Properties().apply { load(FileInputStream(File("local.properties"))) }
        }

        fun getValue(key: String): String {
            return properties.getProperty(key)
        }
    }
}