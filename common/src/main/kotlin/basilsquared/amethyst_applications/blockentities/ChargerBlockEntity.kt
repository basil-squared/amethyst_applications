package basilsquared.amethyst_applications.blockentities

import basilsquared.amethyst_applications.ModBlockEntities
import basilsquared.amethyst_applications.menus.ChargerMenu
import com.mojang.serialization.Decoder
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.Container
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class ChargerBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModBlockEntities.CHARGER.get(),pos,state),
    MenuProvider {
    private val slots = 2
    private var progress = 0
    private var maxProgress = 200
    private var energyCapacity = 10000
    private var energyStored = 0
    val inventory = SimpleContainer(slots)
    val data = object: ContainerData {

        /**
         * This is how the GUI reads data
         * index tells it what to read
         * has handling for values 'out of its scope' I.E >1
         * Cool!
         */
        override fun get(index: Int): Int {
            /*
            When is like a fancy case switch
            its like
            'when its 0, return progress'
            'when its 1, return maxProgress'
            very very cool very very cool
             */
            return when (index) {
                0 -> progress
                1 -> maxProgress
                2 -> energyCapacity
                3 -> energyStored
                else -> 0
            }
        }

        /**
         * This is when it wants to write data
         * index is which data to write
         * value is the actual value of the data
         */
        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> progress = value
                1 -> maxProgress = value
                2 -> energyCapacity = value
                3 -> energyStored = value
            }
        }
        // Tells minecraft 'hey! I have 2 slots!'
        override fun getCount() = slots
    }
    init {
        inventory.addListener { setChanged() }
    }

    /**
     * Gets the human-readable display name of
     * the block entity. Pulls from (i believe)
     * the JSON file? yeah
     */
    override fun getDisplayName(): Component {
       return Component.translatable("container.amethyst_applications.charger")
    }

    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player): AbstractContainerMenu {
        return ChargerMenu(containerId, playerInventory, this, this.data)
    }


    /**
     * This is for when player is saving data,
     * wraps up all my data in a pretty little bow and saves it
     * to world file or whatever else
     * @param tag The Inputted CompoundTag to save to
     */
    override fun saveAdditional(tag: CompoundTag) {
        // first line here tells mc to save all the rudimentary data
        super.saveAdditional(tag)
        tag.putInt("Progress",progress)
        tag.putInt("MaxProgress",maxProgress)
        tag.putInt("EnergyCapacity",energyCapacity)
        tag.putInt("EnergyStored",energyStored)

        val items = CompoundTag()
        // iterate over the items in the inv so we can get everything
        for (i in 0 until inventory.containerSize) {
            val stack = inventory.getItem(i)
            // if the particular slot isn't empty, let's save it for later
            if (!stack.isEmpty) {
                val itemTag = CompoundTag()
                stack.save(itemTag)
                items.put("Slot$i",itemTag)
            }
        }
        // finally, let's save the big compoundtag to our nbt
        tag.put("Inventory", items)

    }

    override fun load(tag: CompoundTag) {
        // Load rudimentary data, like we saved it last time
        super.load(tag)
        // loading the simple stuff here
        progress = tag.getInt("Progress")
        maxProgress = tag.getInt("MaxProgress")
        energyCapacity = tag.getInt("EnergyCapacity")
        energyStored = tag.getInt("EnergyStored")
        // now for the big evil item loading :)
        val items = tag.getCompound("Inventory")

        // once again, iterating over max size
        for (i in 0 until inventory.containerSize) {
            // if our nbt contains data for that slot, load it i
            if (items.contains("Slot$i")) {
                val stack = ItemStack.of(items.getCompound("Slot$i"))
                inventory.setItem(i,stack)
            }
        }
    }
    fun getInventory(): SimpleContainer = inventory

}