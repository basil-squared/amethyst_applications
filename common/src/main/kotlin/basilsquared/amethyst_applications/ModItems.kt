package basilsquared.amethyst_applications

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item

object ModItems {
    private val ITEMS = DeferredRegister.create(Constants.MOD_ID,Registries.ITEM)
    fun registerModItem(properties: Item.Properties, name: String, registry: DeferredRegister<Item>): RegistrySupplier<Item> {
        val toReg: RegistrySupplier<Item> = registry.register(name) {
            Item(properties)
        }
        return toReg
    }


    val OBSIDIAN_POWDER = registerModItem(Item.Properties(),"obsidian_powder",ITEMS)
    val CRYSTAL_GLASS: RegistrySupplier<Item> = ITEMS.register("crystal_glass") {
        BlockItem(ModBlocks.CRYSTAL_GLASS.get(), Item.Properties().stacksTo(16))
    }
    fun register() {
        ITEMS.register()
    }
}