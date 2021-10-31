package org.powbot.opensource.slayer

import org.powbot.api.rt4.walking.model.Skill
import org.powbot.api.script.paint.PaintBuilder

class Painter(val script: Slayer) {

    init {
        val paint = PaintBuilder()
            .addString("Last leaf") { script.lastLeaf.name }
            .addString("Current Task") { script.currentTask?.target?.name?.lowercase() }
            .addString("Remaining") { Slayer.taskRemainder().toString() }
            .trackSkill(Skill.Slayer)
            .trackSkill(Skill.Hitpoints)
            .trackSkill(Skill.Attack)
            .trackSkill(Skill.Strength)
            .trackSkill(Skill.Defence)
            .trackSkill(Skill.Ranged)
            .trackSkill(Skill.Magic)
        script.addPaint(paint.build())
    }
}