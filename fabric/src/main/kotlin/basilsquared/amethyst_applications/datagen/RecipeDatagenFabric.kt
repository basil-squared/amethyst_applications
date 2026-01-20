package basilsquared.amethyst_applications.datagen
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.FinishedRecipe
import java.util.function.Consumer

class RecipeDatagenFabric(output: FabricDataOutput): FabricRecipeProvider(output) {
    override fun buildRecipes(finishedRecipe: Consumer<FinishedRecipe>) {

    }

}