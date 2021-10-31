package org.powbot.opensource.slayer.tree.branch

import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent
import org.powbot.opensource.slayer.Slayer
import org.powbot.opensource.slayer.tree.leaf.HandleBank
import org.powbot.opensource.slayer.tree.leaf.OpenBank

class ShouldBank(script: Slayer) : Branch<Slayer>(script, "Should Bank?") {
    override val failedComponent: TreeComponent<Slayer> = Killing(script)
    override val successComponent: TreeComponent<Slayer> = ShouldOpenBank(script)

    override fun validate(): Boolean {
        val hasRequirements = script.currentTask!!.target.requirements.all { it.meets() }
        return !hasRequirements || Inventory.stream().name(script.food).isEmpty()
    }

}

class ShouldOpenBank(script: Slayer) : Branch<Slayer>(script, "Should open Bank?") {
    override val failedComponent: TreeComponent<Slayer> = HandleBank(script)
    override val successComponent: TreeComponent<Slayer> = OpenBank(script)

    override fun validate(): Boolean {
        return !Bank.opened()
    }


}
