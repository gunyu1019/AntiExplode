package kr.yhs.antiExplode.listener

import kr.yhs.antiExplode.AntiExplode
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockExplodeEvent

class BlockListener(private val plugin: AntiExplode) : BaseListener(), Listener {
    @EventHandler
    fun onBlockExplode(block: BlockExplodeEvent) {
        if (plugin.debug) {
            Bukkit.getLogger().info("Block Explode: ${block.block.type} ${block.blockList()}")
        }

        if (plugin.globalBlockExplode) {
            block.isCancelled = true
        }
    }
}