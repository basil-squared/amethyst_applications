package basilsquared.amethyst_applications.botariumreqitems

import basilsquared.amethyst_applications.blockentities.ChargerBlockEntity
import dev.architectury.registry.menu.ExtendedMenuProvider
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu

import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import java.util.Properties

class Charger(properties: BlockBehaviour.Properties): Block(properties), EntityBlock {
    @Deprecated("Deprecated in Java")
    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult
    ): InteractionResult {
        if (!level.isClientSide) {
            val blockEntity = level.getBlockEntity(pos)
            if (blockEntity is ChargerBlockEntity) {
                player.openMenu(object: ExtendedMenuProvider {
                    override fun createMenu(containerId: Int, playerInventory: Inventory,player: Player): AbstractContainerMenu {
                        return blockEntity.createMenu(containerId,playerInventory,player)!!
                    }
                    override fun getDisplayName(): Component {
                        return blockEntity.displayName
                    }

                    override fun saveExtraData(buf: FriendlyByteBuf) {
                        //write data to buffer
                        buf.writeBlockPos(pos)
                    }
                }


                )
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide)
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return ChargerBlockEntity(pos, state)
    }
}

