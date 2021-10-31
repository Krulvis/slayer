package org.powbot.opensource.slayer.tree.branch

import org.powbot.api.Condition
import org.powbot.api.rt4.Actor
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Npc
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.walking.local.Utils
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.SimpleLeaf
import org.powbot.api.script.tree.TreeComponent
import org.powbot.opensource.slayer.Slayer

class Killing(script: Slayer) : Branch<Slayer>(script, "Killing?") {
    override val failedComponent: TreeComponent<Slayer> = NearTarget(script)
    override val successComponent: TreeComponent<Slayer> = SimpleLeaf(script, "Killing..") {
        val interacting = Players.local().interacting()
        Condition.wait { interacting == Actor.Nil || interacting.healthPercent() == 0 }
    }

    override fun validate(): Boolean {
        return killing()
    }

    companion object {
        fun killing(): Boolean {
            val interacting = Players.local().interacting()
            return interacting != Actor.Nil && (!interacting.healthBarVisible() || interacting.healthPercent() > 0)
        }
    }
}

class NearTarget(script: Slayer) : Branch<Slayer>(script, "Near target?") {
    override val successComponent: TreeComponent<Slayer> = SimpleLeaf(script, "Attacking") {
        if (Utils.walkAndInteract(target, "Attack")) {
            Condition.wait({ Killing.killing() }, 250, 15)
        }
    }
    override val failedComponent: TreeComponent<Slayer> = SimpleLeaf(script, "Walking to spot") {
        Movement.walkTo(script.currentTask!!.location.centerTile)
    }

    var target: Npc? = null

    override fun validate(): Boolean {
        target = script.currentTask!!.target()
        return target != null
    }


}
