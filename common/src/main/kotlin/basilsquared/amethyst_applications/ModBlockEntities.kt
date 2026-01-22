package basilsquared.amethyst_applications

import basilsquared.amethyst_applications.blockentities.ChargerBlockEntity
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntityType

object ModBlockEntities {
    private val BLOCK_ENTITIES = DeferredRegister.create(Constants.MOD_ID,Registries.BLOCK_ENTITY_TYPE)
    val CHARGER: RegistrySupplier<BlockEntityType<ChargerBlockEntity>> =
        BLOCK_ENTITIES.register("charger") {
            BlockEntityType.Builder.of(
                ::ChargerBlockEntity,
                ModBlocks.CHARGER.get()
            ).build(null)
        }
}