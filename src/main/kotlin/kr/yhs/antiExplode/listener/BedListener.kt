package kr.yhs.antiExplode.listener

import io.papermc.paper.event.player.PlayerBedFailEnterEvent
import kr.yhs.antiExplode.AntiExplode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class BedListener(private val plugin: AntiExplode) : BaseListener(), Listener {
    @EventHandler
    fun onBedFailEnter(event: PlayerBedFailEnterEvent) {
        if (
            (event.failReason == PlayerBedFailEnterEvent.FailReason.NOT_POSSIBLE_HERE || event.willExplode)
            && getExplodeConfig("BED", event.player.world)
        ) {
            event.isCancelled = true
            sendMessage(event.player, "BED")
        }
    }
}
