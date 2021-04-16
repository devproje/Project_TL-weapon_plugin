package org.projecttl.plugin.weapon.listeners

import org.bukkit.Effect
import org.bukkit.Sound
import org.bukkit.entity.Arrow
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.projecttl.plugin.weapon.utils.Pistol

class PistolListener: Listener {

    @EventHandler
    fun onEvent(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action

        val playerMainHand = player.inventory.itemInMainHand

        if (action == Action.RIGHT_CLICK_AIR && action == Action.RIGHT_CLICK_BLOCK) {
            if (player.name == "Project_TL" && playerMainHand.type == Pistol.itemStack().type) {
                if (playerMainHand.itemMeta.displayName == Pistol.getItemName() && playerMainHand.itemMeta.customModelData == Pistol.getCustomModelData()) {
                    val bullet: Projectile = player.launchProjectile(Arrow::class.java).let { bullet ->
                        bullet.velocity = player.location.direction.multiply(1.5)
                        bullet.world.playEffect(bullet.location, Effect.SMOKE, 10)

                        bullet
                    }

                    bullet.world.playSound(
                        player.location,
                        Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST,
                        100.toFloat(),
                        1.toFloat()
                    )
                }
            }
        }
    }
}