import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.util.*

class LocalHelper {
    object LocalHelper {
        private val logger = LoggerFactory.getLogger(LocalHelper::class.java)

        const val DISCORD_API = "DISCORD_API"
        const val DEV_MODE = "DEV_MODE"
        private var isPropertiesLoaded = true

        private val properties by lazy {
            logger.info("Loading properties")
            Properties().apply {
                if (File(".env").exists()) {
                    load(FileInputStream(File(".env")))
                    logger.info("Loaded .env")
                    isPropertiesLoaded = true
                } else if (File("local.properties").exists()) {
                    load(FileInputStream(File("local.properties")))
                    logger.info("Loaded local.properties")
                    isPropertiesLoaded = true
                } else {
                    logger.info("No local.properties or .env found. Loading from arguments")
                }
            }
        }

        fun getValue(key: String): String? {
            return properties.getProperty(key)
        }
    }
}