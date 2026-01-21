package basilsquared.amethyst_applications.datagen
import basilsquared.amethyst_applications.Constants
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.Resource
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import java.util.concurrent.CompletableFuture

class ModItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>): FabricTagProvider.ItemTagProvider(output,registriesFuture) {
    companion object {
        val OBSIDIAN_POWDERS: TagKey<Item> = TagKey.create(
            BuiltInRegistries.ITEM.key(),
            ResourceLocation("c", "obsidian_powders")
        )
    }
    override fun addTags(provider: HolderLookup.Provider) {
        getOrCreateTagBuilder(OBSIDIAN_POWDERS)
            .add(BuiltInRegistries.ITEM.get(ResourceLocation(Constants.MOD_ID,"obsidian_powder")))
    }
}