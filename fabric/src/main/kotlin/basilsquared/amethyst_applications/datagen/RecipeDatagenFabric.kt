package basilsquared.amethyst_applications.datagen
import basilsquared.amethyst_applications.Constants
import basilsquared.amethyst_applications.ModBlocks
import basilsquared.amethyst_applications.ModItems
import basilsquared.amethyst_applications.ModUtils
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import java.util.function.Consumer

class RecipeDatagenFabric(output: FabricDataOutput): FabricRecipeProvider(output) {

    companion object {}
    val OBSIDIAN_POWDERS: TagKey<Item> = TagKey.create(
        BuiltInRegistries.ITEM.key(),
        ResourceLocation("c","obsidian_powders")
    )
    override fun buildRecipes(finishedRecipe: Consumer<FinishedRecipe>) {

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUtils.modItem("obsidian_powder"),3)
            .requires(Blocks.OBSIDIAN)
            .requires(Items.IRON_NUGGET)
            .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
            .save(finishedRecipe)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModUtils.modBlock("crystal_glass"))
            .pattern("PAP")
            .pattern("AGA")
            .pattern("PAP")
            .define('P', OBSIDIAN_POWDERS)
            .define('A', Items.AMETHYST_SHARD)
            .define('G',Items.GLASS)

    }

}