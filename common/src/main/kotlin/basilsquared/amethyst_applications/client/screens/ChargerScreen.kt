package basilsquared.amethyst_applications.client.screens

import basilsquared.amethyst_applications.Constants
import basilsquared.amethyst_applications.menus.ChargerMenu
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory
import basilsquared.amethyst_applications.client.utils.GuiUtils
class ChargerScreen(val containerMenu: ChargerMenu, inventory: Inventory, component: Component): AbstractContainerScreen<ChargerMenu>(containerMenu, inventory,component) {
    private val bgText = ResourceLocation(Constants.MOD_ID, "charger_text")

    private var progress = containerMenu.getProgress()
    private val maxProgress = containerMenu.getMaxProgress()
    override fun containerTick() {
        progress = containerMenu.getProgress() // Update the progress each tick, will change to every other if laggy.

    }
    override fun renderBg(gui: GuiGraphics, tick: Float, mx: Int,my: Int) {
        gui.blit(bgText,mx,my,0,0,imageWidth,imageHeight)
        GuiUtils.drawProgressBar(gui,GuiUtils.AME_PROGBAR,5,5,25,25,progress,maxProgress,false)

    }
}