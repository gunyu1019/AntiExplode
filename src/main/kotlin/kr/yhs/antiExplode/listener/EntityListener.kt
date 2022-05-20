package kr.yhs.antiExplode.listener

import kr.yhs.antiExplode.AntiExplode
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent

class EntityListener(private val plugin: AntiExplode): BaseListener(), Listener {
    private fun entityCheck(entity: Entity) = getExplodeConfig(entity.type.name, entity.world)

    @EventHandler
    fun onEntityExplode(entity: EntityExplodeEvent) {
        if (plugin.debug) {
            Bukkit.getLogger().info("AntiExplode - Entity Explode: ${entity.entity.type}")
        }

        if (plugin.globalEntityExplode && entityCheck(entity.entity)) {
            entity.isCancelled = true
        }
    }
}