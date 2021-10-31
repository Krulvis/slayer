package org.powbot.opensource.slayer.tree.leaf

import org.powbot.api.Condition
import org.powbot.api.Notifications
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.script.ScriptManager
import org.powbot.opensource.slayer.Slayer
import org.powbot.opensource.slayer.task.ENCHANTED_GEM

class CheckTask(script: Slayer) : Leaf<Slayer>(script, "Checking task") {

    override fun execute() {
        val gem = Inventory.stream().id(ENCHANTED_GEM).firstOrNull()
        if (gem == null) {
            Notifications.showNotification("There's an active task but no gem to check which one")
            script.log.info("No gem in inventory even though there's an active task")
            ScriptManager.stop()
            return
        }
        if (gem.interact("Check"))
            Condition.wait({ script.currentTask != null }, 250, 10)
    }
}