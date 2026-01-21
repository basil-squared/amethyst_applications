package basilsquared.amethyst_applications.datagen

import basilsquared.amethyst_applications.ModBlocks
import basilsquared.amethyst_applications.ModUtils
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import java.util.concurrent.CompletableFuture

class ModBlockTagsDatagenFabric(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>): FabricTagProvider.BlockTagProvider(output,  registriesFuture) {
    override fun addTags(provider: HolderLookup.Provider) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModUtils.modBlock("crystal_glass"))
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModUtils.modBlock("crystal_glass"))
    }
}