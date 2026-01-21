package basilsquared.amethyst_applications

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor

object ModBlocks {
    /**
     *
     */
    fun registerModBlock(properties: BlockBehaviour.Properties, name: String, registry: DeferredRegister<Block>): RegistrySupplier<Block> {
        val toReg: RegistrySupplier<Block> = registry.register(name) {
            Block(
                properties
            )
        }
        return toReg
    }

    // Generic block class for anything that doesn't have special properties
    private val BLOCKS = DeferredRegister.create(Constants.MOD_ID,Registries.BLOCK)

    /**
     * Crystal glass is reinforced glass obtained by crafting amethyst shards
     * with obsidian powder and glass.
     * It bears some of the properties of obsidian, while
     * still being 'Glassy'
     */
    val CRYSTAL_GLASS = registerModBlock(
        BlockBehaviour.Properties.of()
            .strength(25.0f,250f)
            .mapColor(MapColor.COLOR_PURPLE)
            .sound(SoundType.GLASS),
        name = "crystal_glass",
        registry = BLOCKS
    )
    fun register() {
        BLOCKS.register()
    }
}