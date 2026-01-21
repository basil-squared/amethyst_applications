package basilsquared.amethyst_applications
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory


object FabricEntrypoint: ModInitializer {

    override fun onInitialize() {
        val logger = LoggerFactory.getLogger("Main")
        logger.info("Hello Amethyst (Fabric!) :) Loading things now...")
        ModBlocks.register()
        ModItems.register()

    }


}