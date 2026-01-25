package basilsquared.amethyst_applications.menus

import basilsquared.amethyst_applications.ModMenuTypes
import basilsquared.amethyst_applications.blockentities.ChargerBlockEntity
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.inventory.SimpleContainerData
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

class ChargerMenu(
    containerId: Int,
    playerInventory: Inventory,
    val blockEntity: ChargerBlockEntity,
    private val data: ContainerData = SimpleContainerData(2)
) : AbstractContainerMenu(ModMenuTypes.CHARGER.get(), containerId) {
        init {
            val inventory = blockEntity.getInventory()
            // Input slot
            addSlot(Slot(inventory, 0, 56, 35))
            // Output slot
            addSlot(Slot(inventory,1,116,35))

            // Player inventory slots, just gonna do these with a loop
            for (row in 0 until 3) {
                for ( col in 0 until 9) {
                    addSlot(Slot(
                        playerInventory,
                        col + row * 9 + 9, // Index
                        8 + col * 18, // Xpos
                        84 + row * 18 // Ypos
                    ))
                }
            }
            // Hotbar slots
            for (col in 0 until 9) {
                addSlot(Slot(
                    playerInventory,
                    col,
                    8 + col * 18,
                    142
                ))
            }
            addDataSlots(data)


        }
    // Getters for client
    fun getProgress(): Int {
        return data.get(0)

    }
    fun getMaxProgress(): Int {
        return data.get(1)

    }
    fun getEnergyCapacity(): Int {
        return data.get(2)
    }
    fun getEnergyStored(): Int {
        return data.get(3)
    }
    // Shift click handling
    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        var result = ItemStack.EMPTY
        val slot = slots[index]
        if (slot.hasItem()) {
            val stack = slot.item
            result = stack.copy()


            // 0 and 1 are from the device
            //everything else is player's
            if (index < 2) {
                // from machine to inv
                if (!moveItemStackTo(stack, 2, slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else {
                // from inv to machine
                if (!moveItemStackTo(stack, 0, 1, false)) {
                    return ItemStack.EMPTY
                }

            }
            if (stack.isEmpty) {
                slot.set(ItemStack.EMPTY)

            } else {
                slot.setChanged()
            }
        }
        return result
    }
    // Validation checking
    // I.E can the player still use the menu?
    override fun stillValid(player:Player):Boolean {
        // does the damned thing even exist?
        if (blockEntity.level?.getBlockEntity(blockEntity.blockPos) != blockEntity) {
            return false
        }
        // is player close enough?
        return player.distanceToSqr(
            blockEntity.blockPos.x + 0.5,
            blockEntity.blockPos.y + 0.5,
            blockEntity.blockPos.z + 0.5
        ) <= 64.0
    }
}