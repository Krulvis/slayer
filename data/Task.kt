package org.powbot.krulvis.slayer.data

import org.powbot.api.Tile

enum class CombatStyle {
    Melee, Magic, Ranged
}

interface Task {

    /**
     * Npc names that fit the task
     */
    val names: List<String>

    /**
     * Optimal style to kill the monster with
     * This can be used to allow (not force) people to specify multiple gear-sets
     *  in GUI for each style
     */
    val optimalStyle: CombatStyle

    /**
     * List of locations where this monster can be killed
     * Multiple is only really needed for Konar tasks
     */
    val locations: List<Location>

}

/**
 * Since Konar specifies a location where the monster needs to be killed
 * we need a way to parse that location and kill the monsters there
 * @param name should be parsable from konar given task
 * @param centerTile center tile of the killing floor where script should walk to
 * @param radius radius from center tile in which monsters can be killed
 * @param safeSpot optional safespot if possible for this location
 */
data class Location(val name: String, val centerTile: Tile, val radius: Int = 10, val safeSpot: Tile = Tile.Nil)