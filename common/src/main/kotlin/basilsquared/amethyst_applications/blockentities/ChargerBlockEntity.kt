package basilsquared.amethyst_applications.blockentities

import basilsquared.amethyst_applications.ModBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.world.MenuProvider
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class ChargerBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModBlockEntities.CHARGER.get(),pos,state),
    MenuProvider {

    private val inventory = object : ItemStackHandler(1) {
        override fun onContentsChanged(slot: Int) {
            setChanged()
        }
    }
}