package basilsquared.amethyst_applications.datagen



import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import org.slf4j.LoggerFactory


class DatagenEntrypoint: DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(datagen: FabricDataGenerator?) {
        val logger = LoggerFactory.getLogger("Datagen")
        logger.info("Hello datagen! Generating data now..")
        val pack = datagen?.createPack()
        pack?.addProvider(::RecipeDatagenFabric)

    }
}