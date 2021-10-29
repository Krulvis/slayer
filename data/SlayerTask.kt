package org.powbot.krulvis.slayer.data

import org.powbot.api.Tile

enum class CombatStyle {
    Melee, Magic, Ranged
}

interface SlayerTask {

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
 * @param dungeon what dungeon is the location at
 * @param centerTile center tile of the killing floor where script should walk to
 * @param radius radius from center tile in which monsters can be killed
 * @param safeSpot optional safespot if possible for this location
 */
data class Location(val dungeon: Dungeon, val centerTile: Tile, val radius: Int = 10, val safeSpot: Tile = Tile.Nil)

enum class Dungeon {
    ABYSS,
    ANCIENT_CAVERN,
    ASGARNIAN_ICE_DUNGEON,
    BATTLEFRONT,
    BRIMHAVEN_DUNGEON,
    BRINE_RAT_CAVERN,
    CATACOMBS_OF_KOUREND,
    CHASM_OF_FIRE,
    DARKMEYER,
    FOSSIL_ISLAND,
    FORTHOS_DUNGEON,
    FREMENNIK_SLAYER_DUNGEON,
    GIANTS_DEN,
    GOD_WARS_DUNGEON,
    IORWERTH_DUNGEON,
    ISLE_OF_SOULS_DUNGEON,
    JORMUNGAND_PRISON,
    KARUULM_SLAYER_DUNGEON,
    KALPHITE_LAIR,
    KALPHITE_CAVE,
    KEBOS_SWAMP,
    KRAKEN_COVE,
    LITHKREN_VAULT,
    LIGHTHOUSE,
    LIZARDMAN_CANYON,
    LIZARDMAN_SETTLEMENT,
    MEIYERDITCH,
    MEIYERDITCH_LABORATORIES,
    MOLCH,
    MOURNER_TUNNELS,
    MOUNT_QUIDAMORTEN,
    MYTHS_GUILD_DUNGEON,
    OGRE_ENCLAVE,
    EVIL_CHICKENS_LAIR,
    SMOKE_DUNGEON,
    SMOKE_DEVIL_DUNGEON,
    SLAYER_TOWER,
    SLEPE,
    STRONGHOLD_SLAYER_CAVE,
    STRONGHOLD_SLAYER_DUNGEON,
    STRONGHOLD_OF_SECURITY,
    TAVERLY_DUNGEON,
    TROLL_STRONGHOLD,
    DEATH_PLATEAU,
    WATERBIRTH_ISLAND,
    WATERFALL_DUNGEON,
    WYVERN_CAVE,
    ZANARIS,
    ;
}