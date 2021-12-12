package org.powbot.opensource.slayer.task

import org.powbot.api.rt4.Actor
import org.powbot.api.rt4.Npc
import org.powbot.api.rt4.Npcs
import org.powbot.api.rt4.Players
import org.powbot.opensource.slayer.Slayer

class SlayerTask(val target: SlayerTarget, val amount: Int, val location: Location) {

    fun onGoing() = Slayer.taskRemainder() > 0

    fun target(): Npc? {
        val targets = Npcs.stream().name(*target.names).filtered {
            val interacting = it.interacting()
            (interacting == Actor.Nil || interacting == Players.local()) && (!it.healthBarVisible() || it.healthPercent() > 0)
        }.reachable().nearest().list()
        return targets.firstOrNull { it.interacting() == Players.local() } ?: targets.firstOrNull()
    }

}