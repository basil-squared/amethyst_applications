package basilsquared.amethyst_applications.client.utils

import basilsquared.amethyst_applications.Constants
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation

/**
 * This is where textures and shit are. lelele
 */
object GuiUtils {
    var AME_PROGBAR: ResourceLocation = ResourceLocation(Constants.MOD_ID,"ame_progbar")
    /**
     * Renders a minecraft-style progress bar on the screen.
     * @param guiGraphics the main class to actually. render the damn thing
     * @param resource The texture to use whenever rendering the bar
     * @param offsetX Offset of text
     * @param offsetY Offset of text
     * @param width width of progbar
     * @param height height of progbar
     * @param prog Progress amt to be rendered
     * @param maxProg Ceiling of progress
     * @param rev If progress needs to be depicted as reversed, set to true
     * @return Absolutely nothing. It renders and that is it.
     * @
     *
     *
     */
    fun drawProgressBar(guiGraphics: GuiGraphics, resource: ResourceLocation, offsetX: Int, offsetY: Int, width: Int, height: Int, prog: Int, maxProg: Int, rev: Boolean ) {
        var widthProg: Int = (width * prog / maxProg)
        if (rev) { widthProg = width - widthProg }
        guiGraphics.blit(resource,offsetX,offsetY,0F,0F,widthProg,height,width,height)

}}