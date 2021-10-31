package org.powbot.opensource.slayer.tree.leaf

import org.powbot.api.Notifications
import org.powbot.api.requirement.ItemRequirement
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.rscache.loader.ItemLoader
import org.powbot.mobile.script.ScriptManager
import org.powbot.opensource.slayer.Slayer
import org.powbot.opensource.slayer.task.COINS
import org.powbot.opensource.slayer.task.ICE_COOLER
import org.powbot.opensource.slayer.task.SHANTAY_PASS
import org.powbot.opensource.slayer.task.WATERSKINS

class HandleBank(script: Slayer) : Leaf<Slayer>(script, "Banking") {

    override fun execute() {
        val requirements = script.currentTask!!.target.requirements
        if (needsShantyPass() && Inventory.stream().id(SHANTAY_PASS).isEmpty()) {
            Bank.withdraw(SHANTAY_PASS, 1)
        }
        requirements.filter { it is ItemRequirement }.forEach {
            val req = (it as ItemRequirement)
            val amount = when {
                (ICE_COOLER in req.ids) -> Slayer.taskRemainder() + 10
                (WATERSKINS.contentEquals(req.ids)) -> 5
                (COINS in req.ids) -> 2000
                else -> req.amount
            }
            val currentAmount = Inventory.stream().id(*req.ids).count(true).toInt()
            if (currentAmount < amount) {
                val id = Bank.stream().id(*req.ids).firstOrNull()?.id ?: -1
                if (id == -1) {
                    script.log.info("Out of $req")
                    Notifications.showNotification("Out of $req")
                    ScriptManager.stop()
                } else
                    Bank.withdraw(id, amount - currentAmount)
            }
        }
        script.inventory.forEach { (id, amount) ->
            val currentAmount = Inventory.stream().id(id).count(true).toInt()
            if (currentAmount < amount) {
                if (!Bank.withdraw(id, amount - currentAmount) && Bank.stream().id(id).isEmpty()) {
                    script.log.info("Out of ${ItemLoader.load(id)?.name}")
                    Notifications.showNotification("Out of ${ItemLoader.load(id)?.name}")
                    ScriptManager.stop()
                }
            }
        }
    }

    fun needsShantyPass(): Boolean = script.currentTask!!.target.requirements
        .any { WATERSKINS.contentEquals((it as ItemRequirement).ids) }

}