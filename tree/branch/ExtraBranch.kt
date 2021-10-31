package org.powbot.opensource.slayer.tree.branch

import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.rt4.Constants
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Skills
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.SimpleLeaf
import org.powbot.api.script.tree.TreeComponent
import org.powbot.opensource.slayer.Slayer

class ShouldEat(script: Slayer) : Branch<Slayer>(script, "Should eat?") {
    override val failedComponent: TreeComponent<Slayer> = ShouldUseItem(script)
    override val successComponent: TreeComponent<Slayer> = SimpleLeaf(script, "Eating") {
        val food = Inventory.stream().name(script.food).firstOrNull()
        val action = food?.actions()?.get(0) ?: "eat"
        if (food?.interact(action) == true) {
            Condition.wait({ missingHp() < nextEatAtMissing }, 250, 10)
            nextEatAtMissing = Random.nextInt(12, 20)
        }
    }

    var nextEatAtMissing = Random.nextInt(12, 20)

    override fun validate(): Boolean {
        return missingHp() >= nextEatAtMissing
    }

    fun currentHp(): Int = Skills.level(Constants.SKILLS_HITPOINTS)
    fun missingHp(): Int = Skills.realLevel(Constants.SKILLS_HITPOINTS) - currentHp()


}