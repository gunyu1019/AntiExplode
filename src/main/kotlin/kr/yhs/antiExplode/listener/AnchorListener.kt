package kr.yhs.antiExplode.listener

import kr.yhs.antiExplode.AntiExplode
import org.bukkit.Material
import org.bukkit.block.data.type.RespawnAnchor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class AnchorListener(private val plugin: AntiExplode) : BaseListener(), Listener {
    @EventHandler
    fun onPlayerInteractEvent(interact: PlayerInteractEvent) {
        if (
            interact.clickedBlock == null
            || interact.clickedBlock?.type != Material.RESPAWN_ANCHOR
            || !getExplodeConfig("RESPAWN_ANCHOR", interact.player.world)
            || interact.player.world.name == "world_nether"
            || interact.action != Action.RIGHT_CLICK_BLOCK
            || interact.hand != EquipmentSlot.HAND
        )
            return

        val blockData = interact.clickedBlock!!.blockData as RespawnAnchor
        if (checkExplode(blockData, interact.material)) {
            interact.isCancelled = true
            sendMessage(interact.player, "RESPAWN_ANCHOR")
        }
    }

    private fun checkExplode(blockData: RespawnAnchor, handMaterial: Material): Boolean =
        (blockData.charges >= blockData.maximumCharges && handMaterial == Material.GLOWSTONE) ||
                (handMaterial != Material.GLOWSTONE && blockData.charges >= 0)
}