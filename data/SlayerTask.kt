package org.powbot.krulvis.slayer.data

import org.powbot.api.Tile
import org.powbot.api.rt4.Npc
import org.powbot.api.rt4.Npcs

/**
 * Since Konar specifies a location where the monster needs to be killed
 * we need a way to parse that location and kill the monsters there
 * @param dungeon what dungeon is the location at
 * @param centerTile center tile of the killing floor where script should walk to
 * @param radius radius from center tile in which monsters can be killed
 * @param safeSpot optional safespot if possible for this location
 */
data class Location(val dungeon: Dungeon, val centerTile: Tile, val radius: Int = 10, val safeSpot: Tile = Tile.Nil)

enum class CombatStyle {
    Melee, Magic, Ranged
}

enum class Master(val tile: Tile) {
    TURAEL(Tile(-1, -1, -1)),
    SPRIA(Tile(-1, -1, -1)),
    KRYSTILIA(Tile(-1, -1, -1)),
    MAZCHNA(Tile(3510, 3507, 0)),
    VANNAKA(Tile(-1, -1, -1)),
    CHAELDAR(Tile(-1, -1, -1)),
    KONAR(Tile(-1, -1, -1)),
    NIEVE(Tile(-1, -1, -1)),
    DURADEL(Tile(-1, -1, -1)),
    ;

    fun master(): Npc? = Npcs.stream().name(name).firstOrNull()

    companion object {
        fun forName(name: String): Master? {
            val name = name.uppercase()
            return values().firstOrNull { it.name == name }
        }
    }
}

enum class SlayerTask(
    /**
     * Npc names that fit the task
     */
    val names: List<String>,

    /**
     * Optimal style to kill the monster with
     * This can be used to allow (not force) people to specify multiple gear-sets
     *  in GUI for each style
     */
    val optimalStyle: CombatStyle,

    /**
     * List of locations where this monster can be killed
     * Multiple is only really needed for Konar tasks
     */
    vararg val locations: Location
) {
    ABERRANT_SPECTRES(
        listOf("Aberrant spectre"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1)),
        Location(Dungeon.STRONGHOLD_SLAYER_CAVE, Tile(-1, -1, -1))
    ),
    ABYSSAL_DEMONS(
        listOf("Abyssal demon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.ABYSS, Tile(-1, -1, -1)),
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1))
    ),
    ADAMANT_DRAGONS(
        listOf("Adamant dragon"),
        CombatStyle.Melee,
        Location(Dungeon.LITHKREN_VAULT, Tile(-1, -1, -1))
    ),
    ANKOU(
        listOf("Ankou"),
        CombatStyle.Melee,
        Location(Dungeon.STRONGHOLD_OF_SECURITY, Tile(-1, -1, -1)),
        Location(Dungeon.STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1))
    ),
    AVIANSIE(
        listOf("Aviansie"),
        CombatStyle.Ranged,
        Location(Dungeon.GOD_WARS_DUNGEON, Tile(-1, -1, -1))
    ),
    BASILISK(
        listOf("Basilisk"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.JORMUNGAND_PRISON, Tile(-1, -1, -1))
    ),
    BLACK_DEMONS(
        listOf("Black demon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.CHASM_OF_FIRE, Tile(-1, -1, -1)),
        Location(Dungeon.TAVERLY_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    BLACK_DRAGONS(
        listOf("Black dragon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.EVIL_CHICKENS_LAIR, Tile(-1, -1, -1)),
        Location(Dungeon.TAVERLY_DUNGEON, Tile(-1, -1, -1)),
    ),
    BLOODVELD(
        listOf("Bloodveld"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.GOD_WARS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.MEIYERDITCH_LABORATORIES, Tile(-1, -1, -1)),
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1)),
        Location(Dungeon.STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
    ),
    BLUE_DRAGONS(
        listOf("Blue dragon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.OGRE_ENCLAVE, Tile(-1, -1, -1)),
        Location(Dungeon.TAVERLY_DUNGEON, Tile(-1, -1, -1)),
    ),
    BRINE_RATS(
        listOf("Brine rat"),
        CombatStyle.Melee,
        Location(Dungeon.BRINE_RAT_CAVERN, Tile(-1, -1, -1)),
    ),
    BRONZE_DRAGON(
        listOf("Bronze dragon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    CAVE_KRAKEN(
        listOf("Cave kraken"),
        CombatStyle.Magic,
        Location(Dungeon.KRAKEN_COVE, Tile(-1, -1, -1)),
    ),
    DAGANNOTH(
        listOf("Dagannoth"),
        CombatStyle.Ranged,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.LIGHTHOUSE, Tile(-1, -1, -1)),
        Location(Dungeon.WATERBIRTH_ISLAND, Tile(-1, -1, -1)),
        Location(Dungeon.JORMUNGAND_PRISON, Tile(-1, -1, -1)),
    ),
    DARK_BEASTS(
        listOf("Dark beast"),
        CombatStyle.Melee,
        Location(Dungeon.MOURNER_TUNNELS, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
    ),
    DRAKES(
        listOf("Drake"),
        CombatStyle.Melee,
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
    ),
    DUST_DEVILS(
        listOf("Dust devil"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.SMOKE_DUNGEON, Tile(-1, -1, -1)),
    ),
    FIRE_GIANTS(
        listOf("Fire giant"),
        CombatStyle.Melee,
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.GIANTS_DEN, Tile(-1, -1, -1)),
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.WATERFALL_DUNGEON, Tile(-1, -1, -1)),
    ),
    FOSSIL_ISLAND_WYVERNS(
        listOf("Wyvern"),
        CombatStyle.Ranged,
        Location(Dungeon.WYVERN_CAVE, Tile(-1, -1, -1)),
    ),
    GARGOYLES(
        listOf("Gargoyle"),
        CombatStyle.Ranged,
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1)),
    ),
    GREATER_DEMONS(
        listOf("Greater demon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.CHASM_OF_FIRE, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    HELLHOUNDS(
        listOf("Hellhound"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.CHASM_OF_FIRE, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    HYDRAS(
        listOf("Hydra"),
        CombatStyle.Melee,
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
    ),
    IRON_DRAGONS(
        listOf("Iron dragon"),
        CombatStyle.Melee,
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
    ),
    JELLIES(
        listOf("Jelly"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
    ),
    KALPHITE(
        listOf("Jelly"),
        CombatStyle.Melee,
        Location(Dungeon.KALPHITE_CAVE, Tile(-1, -1, -1)),
        Location(Dungeon.KALPHITE_LAIR, Tile(-1, -1, -1)),
    ),
    KURASKS(
        listOf("Kurask"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
    ),
    LIZARDMEN(
        listOf("Lizardman"),
        CombatStyle.Melee,
        Location(Dungeon.BATTLEFRONT, Tile(-1, -1, -1)),
        Location(Dungeon.LIZARDMAN_CANYON, Tile(-1, -1, -1)),
        Location(Dungeon.LIZARDMAN_SETTLEMENT, Tile(-1, -1, -1)),
        Location(Dungeon.KEBOS_SWAMP, Tile(-1, -1, -1)),
        Location(Dungeon.MOLCH, Tile(-1, -1, -1)),
    ),
    MITHRIL_DRAGONS(
        listOf("Mithril dragon"),
        CombatStyle.Melee,
        Location(Dungeon.ANCIENT_CAVERN, Tile(-1, -1, -1)),
    ),
    MUTATED_ZYGOMITES(
        listOf("Mutated zygomite"),
        CombatStyle.Melee,
        Location(Dungeon.FOSSIL_ISLAND, Tile(-1, -1, -1)),
        Location(Dungeon.ZANARIS, Tile(-1, -1, -1)),
    ),
    NECHRYAEL(
        listOf("Nechryael"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1)),
    ),
    RED_DRAGONS(
        listOf("Red dragon"),
        CombatStyle.Melee,
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.FORTHOS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
    ),
    RUNE_DRAGONS(
        listOf("Rune dragon"),
        CombatStyle.Melee,
        Location(Dungeon.LITHKREN_VAULT, Tile(-1, -1, -1)),
    ),
    SKELETAL_WYVERNS(
        listOf("Skeletal wyvern"),
        CombatStyle.Melee,
        Location(Dungeon.ASGARNIAN_ICE_DUNGEON, Tile(-1, -1, -1)),
    ),
    SMOKE_DEVILS(
        listOf("Smoke devil"),
        CombatStyle.Melee,
        Location(Dungeon.SMOKE_DEVIL_DUNGEON, Tile(-1, -1, -1)),
    ),
    STEEL_DRAGONS(
        listOf("Steel dragon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    TROLLS(
        listOf("Troll"),
        CombatStyle.Melee,
        Location(Dungeon.TROLL_STRONGHOLD, Tile(-1, -1, -1)),
        Location(Dungeon.DEATH_PLATEAU, Tile(-1, -1, -1)),
        Location(Dungeon.MOUNT_QUIDAMORTEN, Tile(-1, -1, -1)),
    ),
    TUROTH(
        listOf("Turoth"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
    ),
    VAMPYRE(
        listOf("Vampyre"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.MEIYERDITCH, Tile(-1, -1, -1)),
        Location(Dungeon.SLEPE, Tile(-1, -1, -1)),
    ),
    WATERFIENDS(
        listOf("Vampyre"),
        CombatStyle.Melee,
        Location(Dungeon.ANCIENT_CAVERN, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.KRAKEN_COVE, Tile(-1, -1, -1)),
    ),
    WYRMS(
        listOf("Wyrm"),
        CombatStyle.Melee,
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
    );
}

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