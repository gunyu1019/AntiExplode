package kr.yhs.antiExplode

import kr.yhs.antiExplode.listener.AnchorListener
import kr.yhs.antiExplode.listener.BedListener
import kr.yhs.antiExplode.listener.BlockListener
import kr.yhs.antiExplode.listener.EntityListener
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class AntiExplode : JavaPlugin(), Listener {
    var debug: Boolean = config.getBoolean("debug")
    val globalBlockExplode: Boolean = config.getBoolean("globalBlockExplode")
    val globalEntityExplode: Boolean = config.getBoolean("globalEntityExplode")

    companion object {
        var instance: AntiExplode? = null
    }

    override fun onEnable() {
        instance = this
        if (!dataFolder.exists()) {
            this.saveDefaultConfig()
            Bukkit.getLogger().info("AntiExplode - Initialized configuration!")
        }
        server.pluginManager.registerEvents(this, this)
        Bukkit.getLogger().info("AntiExplode - Minecraft listener registered!")
        server.pluginManager.apply {
            registerEvents(AnchorListener(this@AntiExplode), this@AntiExplode)
            registerEvents(BedListener(this@AntiExplode), this@AntiExplode)
            registerEvents(BlockListener(this@AntiExplode), this@AntiExplode)
            registerEvents(EntityListener(this@AntiExplode), this@AntiExplode)
        }
        Bukkit.getLogger().info("AntiExplode - AntiExplode Plugin load done.")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}