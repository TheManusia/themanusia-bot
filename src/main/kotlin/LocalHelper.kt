import java.io.File
import java.io.FileInputStream
import java.util.*

class LocalHelper {
    object LocalHelper {

        const val DISCORD_API = "DISCORD_API"
        const val DEV_MODE = "DEV_MODE"
        private var isPropertiesLoaded = false

        private val properties by lazy {
            Properties().apply {
                if (File(".env").exists()) {
                    load(FileInputStream(File(".env")))
                } else if (File("local.properties").exists()) {
                    load(FileInputStream(File("local.properties")))
                } else {
                    isPropertiesLoaded = true
                }
            }
        }

        fun isPropertiesLoaded(): Boolean {
            return isPropertiesLoaded
        }

        fun getValue(key: String): String {
            return properties.getProperty(key)
        }
    }
}