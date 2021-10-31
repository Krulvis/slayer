package org.powbot.opensource.slayer.task

import org.powbot.api.rt4.Npc
import org.powbot.api.rt4.Npcs
import org.powbot.opensource.slayer.Slayer

class SlayerTask(val target: SlayerTarget, val amount: Int, val location: Location) {

    fun onGoing() = Slayer.taskRemainder() > 0

    fun target(): Npc? =
        Npcs.stream().name(*target.names).filtered { !it.healthBarVisible() || it.healthPercent() > 0 }
            .reachable()
            .firstOrNull()
}