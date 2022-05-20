package kr.yhs.antiExplode.listener

import kr.yhs.antiExplode.AntiExplode
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.title.Title
import org.bukkit.World
import org.bukkit.entity.Player

abstract class BaseListener {
    fun getExplodeConfig(name: String, world: World): Boolean =
        AntiExplode.instance?.config?.getConfigurationSection(world.name)?.getBoolean(name) ?: false

    private fun formatPlayer(player: Player, comment: String): String =
        comment.replace("{player}", player.name.toString())

    fun sendMessage(player: Player, name: String) {
        val comment: String =
            AntiExplode.instance?.config?.getConfigurationSection("comment")?.getString(name) ?: return
        sendComment(player, comment)
    }

    private fun sendComment(
        player: Player,
        comment: String
    ) = player.sendMessage(
        formatPlayer(player, comment)
    )
}