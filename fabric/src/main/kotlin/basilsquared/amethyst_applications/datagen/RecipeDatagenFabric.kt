package basilsquared.amethyst_applications.datagen
import basilsquared.amethyst_applications.ModBlocks
import basilsquared.amethyst_applications.ModItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import java.util.function.Consumer

class RecipeDatagenFabric(output: FabricDataOutput): FabricRecipeProvider(output) {
    override fun buildRecipes(finishedRecipe: Consumer<FinishedRecipe>) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OBSIDIAN_POWDER.get(),3)
            .requires(Blocks.OBSIDIAN)
            .requires(Items.IRON_NUGGET)
            .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
            .save(finishedRecipe)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRYSTAL_GLASS.get())
            .pattern("PAP")
            .pattern("AGA")
            .pattern("PAP")
            .define('P', ModItems.OBSIDIAN_POWDER.get())
            .define('A', Items.AMETHYST_SHARD)
            .define('G',Items.GLASS)

    }

}