package org.powbot.opensource.slayer.task

import org.powbot.api.Tile
import org.powbot.api.requirement.ItemRequirement
import org.powbot.api.requirement.Requirement
import org.powbot.api.rt4.Equipment
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Npc
import org.powbot.api.rt4.Npcs
import org.powbot.mobile.script.ScriptManager

/**
 * Since Konar specifies a location where the monster needs to be killed
 * we need a way to parse that location and kill the monsters there
 * @param dungeon what dungeon is the location at
 * @param centerTile center tile of the killing floor where script should walk to
 * @param radius radius from center tile in which monsters can be killed
 * @param safeSpot optional safespot if possible for this location
 */
data class Location(val dungeon: Dungeon, val centerTile: Tile, val radius: Int = 10, val safeSpot: Tile = Tile.Nil)

/**
 * Item requirement we can use to use on the npc to kill them
 */
class KillItemRequirement(id: Int, amount: Int) : ItemRequirement(id, amount, ItemRequirementType.INVENTORY)
class LightRequirement : Requirement {
    override fun meets(): Boolean {
        return Inventory.stream().id(*LIGHTS).isNotEmpty() || Equipment.stream().id(*LIGHTS).isNotEmpty()
    }
}

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
    STEVE(Tile(-1, -1, -1)),
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

/**
 * enum class with possible targets and their requirements + locations
 */
enum class SlayerTarget(
    /**
     * Npc names that fit the task
     */
    val names: Array<String>,

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
    vararg val locations: Location,

    /**
     * Requirements necessary for the task (items, equippables)
     */
    val requirements: List<Requirement> = emptyList<ItemRequirement>()
) {
    ABERRANT_SPECTRES(
        arrayOf("Aberrant spectre"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1)),
        Location(Dungeon.STRONGHOLD_SLAYER_CAVE, Tile(-1, -1, -1))
    ),
    ABYSSAL_DEMONS(
        arrayOf("Abyssal demon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.ABYSS, Tile(-1, -1, -1)),
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1))
    ),
    ADAMANT_DRAGONS(
        arrayOf("Adamant dragon"),
        CombatStyle.Melee,
        Location(Dungeon.LITHKREN_VAULT, Tile(-1, -1, -1))
    ),
    ANKOU(
        arrayOf("Ankou"),
        CombatStyle.Melee,
        Location(Dungeon.STRONGHOLD_OF_SECURITY, Tile(-1, -1, -1)),
        Location(Dungeon.STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1))
    ),
    AVIANSIE(
        arrayOf("Aviansie"),
        CombatStyle.Ranged,
        Location(Dungeon.GOD_WARS_DUNGEON, Tile(-1, -1, -1))
    ),
    BASILISK(
        arrayOf("Basilisk"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.JORMUNGAND_PRISON, Tile(-1, -1, -1))
    ),
    BEARS(
        arrayOf("Grizzly bear"),
        CombatStyle.Melee,
        Location(Dungeon.NIL, Tile(2699, 3331, 0))
    ),
    BLACK_DEMONS(
        arrayOf("Black demon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.CHASM_OF_FIRE, Tile(-1, -1, -1)),
        Location(Dungeon.TAVERLY_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    BLACK_DRAGONS(
        arrayOf("Black dragon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.EVIL_CHICKENS_LAIR, Tile(-1, -1, -1)),
        Location(Dungeon.TAVERLY_DUNGEON, Tile(-1, -1, -1)),
    ),
    BLOODVELD(
        arrayOf("Bloodveld"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.GOD_WARS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.MEIYERDITCH_LABORATORIES, Tile(-1, -1, -1)),
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1)),
        Location(Dungeon.STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
    ),
    BLUE_DRAGONS(
        arrayOf("Blue dragon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.OGRE_ENCLAVE, Tile(-1, -1, -1)),
        Location(Dungeon.TAVERLY_DUNGEON, Tile(-1, -1, -1)),
    ),
    BRINE_RATS(
        arrayOf("Brine rat"),
        CombatStyle.Melee,
        Location(Dungeon.BRINE_RAT_CAVERN, Tile(-1, -1, -1)),
    ),
    BRONZE_DRAGON(
        arrayOf("Bronze dragon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    CAVE_KRAKEN(
        arrayOf("Cave kraken"),
        CombatStyle.Magic,
        Location(Dungeon.KRAKEN_COVE, Tile(-1, -1, -1)),
    ),
    CAVE_BUG(
        arrayOf("Cave bug"),
        CombatStyle.Melee,
        Location(Dungeon.NIL, Tile(3188, 9557, 0)),
    ),
    DAGANNOTH(
        arrayOf("Dagannoth"),
        CombatStyle.Ranged,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.LIGHTHOUSE, Tile(-1, -1, -1)),
        Location(Dungeon.WATERBIRTH_ISLAND, Tile(-1, -1, -1)),
        Location(Dungeon.JORMUNGAND_PRISON, Tile(-1, -1, -1)),
    ),
    DARK_BEASTS(
        arrayOf("Dark beast"),
        CombatStyle.Melee,
        Location(Dungeon.MOURNER_TUNNELS, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
    ),
    DRAKES(
        arrayOf("Drake"),
        CombatStyle.Melee,
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
    ),
    DUST_DEVILS(
        arrayOf("Dust devil"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.SMOKE_DUNGEON, Tile(-1, -1, -1)),
    ),
    FIRE_GIANTS(
        arrayOf("Fire giant"),
        CombatStyle.Melee,
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.GIANTS_DEN, Tile(-1, -1, -1)),
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.WATERFALL_DUNGEON, Tile(-1, -1, -1)),
    ),
    FLESHCRAWLERS(
        arrayOf("Fleshcrawler"),
        CombatStyle.Melee,

        ),
    FOSSIL_ISLAND_WYVERNS(
        arrayOf("Wyvern"),
        CombatStyle.Ranged,
        Location(Dungeon.WYVERN_CAVE, Tile(-1, -1, -1)),
    ),
    GARGOYLES(
        arrayOf("Gargoyle"),
        CombatStyle.Ranged,
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1)),
    ),
    GHOULS(
        arrayOf("Ghoul"),
        CombatStyle.Melee,
        Location(Dungeon.NIL, Tile(3429, 3462, 0))
    ),
    GREATER_DEMONS(
        arrayOf("Greater demon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.CHASM_OF_FIRE, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    HELLHOUNDS(
        arrayOf("Hellhound"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.CHASM_OF_FIRE, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    HYDRAS(
        arrayOf("Hydra"),
        CombatStyle.Melee,
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
    ),
    IRON_DRAGONS(
        arrayOf("Iron dragon"),
        CombatStyle.Melee,
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
    ),
    JELLIES(
        arrayOf("Jelly"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
    ),
    KALPHITE(
        arrayOf("Jelly"),
        CombatStyle.Melee,
        Location(Dungeon.KALPHITE_CAVE, Tile(-1, -1, -1)),
        Location(Dungeon.KALPHITE_LAIR, Tile(-1, -1, -1)),
    ),
    KURASKS(
        arrayOf("Kurask"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
    ),
    LIZARDMEN(
        arrayOf("Lizardman"),
        CombatStyle.Melee,
        Location(Dungeon.BATTLEFRONT, Tile(-1, -1, -1)),
        Location(Dungeon.LIZARDMAN_CANYON, Tile(-1, -1, -1)),
        Location(Dungeon.LIZARDMAN_SETTLEMENT, Tile(-1, -1, -1)),
        Location(Dungeon.KEBOS_SWAMP, Tile(-1, -1, -1)),
        Location(Dungeon.MOLCH, Tile(-1, -1, -1)),
    ),
    LIZARDS(
        arrayOf("Desert lizard", "Lizard", "Small lizard"),
        CombatStyle.Melee,
        Location(Dungeon.NIL, Tile(3415, 3033, 0)),
        requirements = listOf(
            ItemRequirement(WATERSKINS, 1, ItemRequirement.ItemRequirementType.INVENTORY),
            KillItemRequirement(ICE_COOLER, 1),
            ItemRequirement(COINS, 1, ItemRequirement.ItemRequirementType.INVENTORY)
        )
    ),
    MITHRIL_DRAGONS(
        arrayOf("Mithril dragon"),
        CombatStyle.Melee,
        Location(Dungeon.ANCIENT_CAVERN, Tile(-1, -1, -1)),
    ),
    MUTATED_ZYGOMITES(
        arrayOf("Mutated zygomite"),
        CombatStyle.Melee,
        Location(Dungeon.FOSSIL_ISLAND, Tile(-1, -1, -1)),
        Location(Dungeon.ZANARIS, Tile(-1, -1, -1)),
    ),
    NECHRYAEL(
        arrayOf("Nechryael"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.SLAYER_TOWER, Tile(-1, -1, -1)),
    ),
    OGRES(
        arrayOf("Ogre"),
        CombatStyle.Melee,
        Location(Dungeon.NIL, Tile(2509, 3111, 0), safeSpot = Tile(2509, 3111, 0)),
    ),
    RED_DRAGONS(
        arrayOf("Red dragon"),
        CombatStyle.Melee,
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.FORTHOS_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
    ),
    ROCKSLUGS(
        arrayOf("Rockslug"),
        CombatStyle.Melee,
        Location(Dungeon.NIL, Tile(3224, 9577, 0)),
        requirements = listOf(
            KillItemRequirement(SALT, 1),
            LightRequirement()
        )
    ),
    RUNE_DRAGONS(
        arrayOf("Rune dragon"),
        CombatStyle.Melee,
        Location(Dungeon.LITHKREN_VAULT, Tile(-1, -1, -1)),
    ),
    SKELETAL_WYVERNS(
        arrayOf("Skeletal wyvern"),
        CombatStyle.Melee,
        Location(Dungeon.ASGARNIAN_ICE_DUNGEON, Tile(-1, -1, -1)),
    ),
    SMOKE_DEVILS(
        arrayOf("Smoke devil"),
        CombatStyle.Melee,
        Location(Dungeon.SMOKE_DEVIL_DUNGEON, Tile(-1, -1, -1)),
    ),
    STEEL_DRAGONS(
        arrayOf("Steel dragon"),
        CombatStyle.Melee,
        Location(Dungeon.CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        Location(Dungeon.BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
    ),
    TROLLS(
        arrayOf("Troll"),
        CombatStyle.Melee,
        Location(Dungeon.TROLL_STRONGHOLD, Tile(-1, -1, -1)),
        Location(Dungeon.DEATH_PLATEAU, Tile(-1, -1, -1)),
        Location(Dungeon.MOUNT_QUIDAMORTEN, Tile(-1, -1, -1)),
    ),
    TUROTH(
        arrayOf("Turoth"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
    ),
    VAMPYRE(
        arrayOf("Vampyre"),
        CombatStyle.Melee,
        Location(Dungeon.FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.MEIYERDITCH, Tile(-1, -1, -1)),
        Location(Dungeon.SLEPE, Tile(-1, -1, -1)),
    ),
    WATERFIENDS(
        arrayOf("Vampyre"),
        CombatStyle.Melee,
        Location(Dungeon.ANCIENT_CAVERN, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.KRAKEN_COVE, Tile(-1, -1, -1)),
    ),
    WYRMS(
        arrayOf("Wyrm"),
        CombatStyle.Melee,
        Location(Dungeon.KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        Location(Dungeon.IORWERTH_DUNGEON, Tile(-1, -1, -1)),
    ),
    ZOMBIE(
        arrayOf("Zombie"),
        CombatStyle.Melee,
        Location(Dungeon.NIL, Tile(3225, 9906, 0)),//Varrock sewers
    );

    fun location(locationText: String): Location {
        val text = locationText.replace(" ", "_")
        val parsedLocation = locations.firstOrNull { it.dungeon.name.equals(text, true) }
        if (parsedLocation == null) {
            ScriptManager.script()?.log?.info("Couldn't parse location: $text")
            return locations.first()
        }
        return parsedLocation
    }

    fun killItem(): KillItemRequirement? =
        requirements.firstOrNull { it is KillItemRequirement } as KillItemRequirement?

    companion object {
        fun forName(name: String): SlayerTarget? {
            return values().firstOrNull { it.name.equals(name, true) }
        }
    }
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
    NIL
    ;
}

val ENCHANTED_GEM = 4155
val ICE_COOLER = 6696
val SALT = 4161
val COINS = 995
val WATERSKINS = intArrayOf(1823, 1825, 1827, 1829)
val EMPTY_WATERSKIN = 1831
val SHANTAY_PASS = 1854
val LIGHTS = intArrayOf(-1)