package basilsquared.amethyst_applications

import basilsquared.amethyst_applications.blockentities.ChargerBlockEntity
import basilsquared.amethyst_applications.menus.ChargerMenu
import dev.architectury.registry.registries.DeferredRegister
import net.minecraft.core.registries.Registries
import net.minecraft.world.inventory.MenuType
import dev.architectury.registry.menu.MenuRegistry
import dev.architectury.registry.registries.RegistrySupplier

object ModMenuTypes {
    private val MENUS = DeferredRegister.create(Constants.MOD_ID, Registries.MENU)

    /**
     * Registry supplier for the charger
     * remember to call it with .get() to actually yank the internal
     * menutype from the registry
     */
    val CHARGER: RegistrySupplier<MenuType<ChargerMenu>> =
        /*
        NOTE: menus vary too much to justify making a util function to handle them
        So just make them one at a time.
         */
        MENUS.register("charger") {

            MenuRegistry.ofExtended { containerId, inventory, buf ->
                // containerId: Int - ID for menu instance
                // inventory: Inventory - Player's inventory
                // buf: FriendlyByteBuf - Data from server to client

                // Read position data from server buffer
                val pos = buf.readBlockPos()

                // gets the charger entity
                val blockEntity = inventory.player.level().getBlockEntity(pos)

                ChargerMenu(
                    containerId,
                    inventory,
                    blockEntity as ChargerBlockEntity
                )

            }
        }
}