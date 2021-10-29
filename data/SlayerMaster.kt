package org.powbot.krulvis.slayer.data

import org.powbot.api.Tile
import org.powbot.krulvis.slayer.data.Dungeon.*

object Slayer {

    /**
     * Parse the task from the chat component
     */
    fun parseTask(): Task {
        TODO("Needs to be implemented")
    }

    enum class Task(
        override val names: List<String>,
        override val optimalStyle: CombatStyle,
        vararg val locs: Location
    ) : SlayerTask {
        ABERRANT_SPECTRES(
            listOf("Aberrant spectre"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(SLAYER_TOWER, Tile(-1, -1, -1)),
            Location(STRONGHOLD_SLAYER_CAVE, Tile(-1, -1, -1))
        ),
        ABYSSAL_DEMONS(
            listOf("Abyssal demon"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(ABYSS, Tile(-1, -1, -1)),
            Location(SLAYER_TOWER, Tile(-1, -1, -1))
        ),
        ADAMANT_DRAGONS(
            listOf("Adamant dragon"),
            CombatStyle.Melee,
            Location(LITHKREN_VAULT, Tile(-1, -1, -1))
        ),
        ANKOU(
            listOf("Ankou"),
            CombatStyle.Melee,
            Location(STRONGHOLD_OF_SECURITY, Tile(-1, -1, -1)),
            Location(STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1))
        ),
        AVIANSIE(
            listOf("Aviansie"),
            CombatStyle.Ranged,
            Location(GOD_WARS_DUNGEON, Tile(-1, -1, -1))
        ),
        BASILISK(
            listOf("Basilisk"),
            CombatStyle.Melee,
            Location(FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(JORMUNGAND_PRISON, Tile(-1, -1, -1))
        ),
        BLACK_DEMONS(
            listOf("Black demon"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(CHASM_OF_FIRE, Tile(-1, -1, -1)),
            Location(TAVERLY_DUNGEON, Tile(-1, -1, -1)),
            Location(BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        ),
        BLACK_DRAGONS(
            listOf("Black dragon"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
            Location(EVIL_CHICKENS_LAIR, Tile(-1, -1, -1)),
            Location(TAVERLY_DUNGEON, Tile(-1, -1, -1)),
        ),
        BLOODVELD(
            listOf("Bloodveld"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(GOD_WARS_DUNGEON, Tile(-1, -1, -1)),
            Location(IORWERTH_DUNGEON, Tile(-1, -1, -1)),
            Location(MEIYERDITCH_LABORATORIES, Tile(-1, -1, -1)),
            Location(SLAYER_TOWER, Tile(-1, -1, -1)),
            Location(STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        ),
        BLUE_DRAGONS(
            listOf("Blue dragon"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
            Location(MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
            Location(OGRE_ENCLAVE, Tile(-1, -1, -1)),
            Location(TAVERLY_DUNGEON, Tile(-1, -1, -1)),
        ),
        BRINE_RATS(
            listOf("Brine rat"),
            CombatStyle.Melee,
            Location(BRINE_RAT_CAVERN, Tile(-1, -1, -1)),
        ),
        BRONZE_DRAGON(
            listOf("Bronze dragon"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        ),
        CAVE_KRAKEN(
            listOf("Cave kraken"),
            CombatStyle.Magic,
            Location(KRAKEN_COVE, Tile(-1, -1, -1)),
        ),
        DAGANNOTH(
            listOf("Dagannoth"),
            CombatStyle.Ranged,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(LIGHTHOUSE, Tile(-1, -1, -1)),
            Location(WATERBIRTH_ISLAND, Tile(-1, -1, -1)),
            Location(JORMUNGAND_PRISON, Tile(-1, -1, -1)),
        ),
        DARK_BEASTS(
            listOf("Dark beast"),
            CombatStyle.Melee,
            Location(MOURNER_TUNNELS, Tile(-1, -1, -1)),
            Location(IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        ),
        DRAKES(
            listOf("Drake"),
            CombatStyle.Melee,
            Location(KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        ),
        DUST_DEVILS(
            listOf("Dust devil"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(SMOKE_DUNGEON, Tile(-1, -1, -1)),
        ),
        FIRE_GIANTS(
            listOf("Fire giant"),
            CombatStyle.Melee,
            Location(BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
            Location(GIANTS_DEN, Tile(-1, -1, -1)),
            Location(KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(STRONGHOLD_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(WATERFALL_DUNGEON, Tile(-1, -1, -1)),
        ),
        FOSSIL_ISLAND_WYVERNS(
            listOf("Wyvern"),
            CombatStyle.Ranged,
            Location(WYVERN_CAVE, Tile(-1, -1, -1)),
        ),
        GARGOYLES(
            listOf("Gargoyle"),
            CombatStyle.Ranged,
            Location(SLAYER_TOWER, Tile(-1, -1, -1)),
        ),
        GREATER_DEMONS(
            listOf("Greater demon"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(CHASM_OF_FIRE, Tile(-1, -1, -1)),
            Location(ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
            Location(KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        ),
        HELLHOUNDS(
            listOf("Hellhound"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(CHASM_OF_FIRE, Tile(-1, -1, -1)),
            Location(ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
            Location(KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        ),
        HYDRAS(
            listOf("Hydra"),
            CombatStyle.Melee,
            Location(KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        ),
        IRON_DRAGONS(
            listOf("Iron dragon"),
            CombatStyle.Melee,
            Location(BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(ISLE_OF_SOULS_DUNGEON, Tile(-1, -1, -1)),
        ),
        JELLIES(
            listOf("Jelly"),
            CombatStyle.Melee,
            Location(FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
        ),
        KALPHITE(
            listOf("Jelly"),
            CombatStyle.Melee,
            Location(KALPHITE_CAVE, Tile(-1, -1, -1)),
            Location(KALPHITE_LAIR, Tile(-1, -1, -1)),
        ),
        KURASKS(
            listOf("Kurask"),
            CombatStyle.Melee,
            Location(FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        ),
        LIZARDMEN(
            listOf("Lizardman"),
            CombatStyle.Melee,
            Location(BATTLEFRONT, Tile(-1, -1, -1)),
            Location(LIZARDMAN_CANYON, Tile(-1, -1, -1)),
            Location(LIZARDMAN_SETTLEMENT, Tile(-1, -1, -1)),
            Location(KEBOS_SWAMP, Tile(-1, -1, -1)),
            Location(MOLCH, Tile(-1, -1, -1)),
        ),
        MITHRIL_DRAGONS(
            listOf("Mithril dragon"),
            CombatStyle.Melee,
            Location(ANCIENT_CAVERN, Tile(-1, -1, -1)),
        ),
        MUTATED_ZYGOMITES(
            listOf("Mutated zygomite"),
            CombatStyle.Melee,
            Location(FOSSIL_ISLAND, Tile(-1, -1, -1)),
            Location(ZANARIS, Tile(-1, -1, -1)),
        ),
        NECHRYAEL(
            listOf("Nechryael"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(IORWERTH_DUNGEON, Tile(-1, -1, -1)),
            Location(SLAYER_TOWER, Tile(-1, -1, -1)),
        ),
        RED_DRAGONS(
            listOf("Red dragon"),
            CombatStyle.Melee,
            Location(BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(FORTHOS_DUNGEON, Tile(-1, -1, -1)),
            Location(MYTHS_GUILD_DUNGEON, Tile(-1, -1, -1)),
        ),
        RUNE_DRAGONS(
            listOf("Rune dragon"),
            CombatStyle.Melee,
            Location(LITHKREN_VAULT, Tile(-1, -1, -1)),
        ),
        SKELETAL_WYVERNS(
            listOf("Skeletal wyvern"),
            CombatStyle.Melee,
            Location(ASGARNIAN_ICE_DUNGEON, Tile(-1, -1, -1)),
        ),
        SMOKE_DEVILS(
            listOf("Smoke devil"),
            CombatStyle.Melee,
            Location(SMOKE_DEVIL_DUNGEON, Tile(-1, -1, -1)),
        ),
        STEEL_DRAGONS(
            listOf("Steel dragon"),
            CombatStyle.Melee,
            Location(CATACOMBS_OF_KOUREND, Tile(-1, -1, -1)),
            Location(BRIMHAVEN_DUNGEON, Tile(-1, -1, -1)),
        ),
        TROLLS(
            listOf("Troll"),
            CombatStyle.Melee,
            Location(TROLL_STRONGHOLD, Tile(-1, -1, -1)),
            Location(DEATH_PLATEAU, Tile(-1, -1, -1)),
            Location(MOUNT_QUIDAMORTEN, Tile(-1, -1, -1)),
        ),
        TUROTH(
            listOf("Turoth"),
            CombatStyle.Melee,
            Location(FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
        ),
        VAMPYRE(
            listOf("Vampyre"),
            CombatStyle.Melee,
            Location(FREMENNIK_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(MEIYERDITCH, Tile(-1, -1, -1)),
            Location(SLEPE, Tile(-1, -1, -1)),
        ),
        WATERFIENDS(
            listOf("Vampyre"),
            CombatStyle.Melee,
            Location(ANCIENT_CAVERN, Tile(-1, -1, -1)),
            Location(IORWERTH_DUNGEON, Tile(-1, -1, -1)),
            Location(KRAKEN_COVE, Tile(-1, -1, -1)),
        ),
        WYRMS(
            listOf("Wyrm"),
            CombatStyle.Melee,
            Location(KARUULM_SLAYER_DUNGEON, Tile(-1, -1, -1)),
            Location(IORWERTH_DUNGEON, Tile(-1, -1, -1)),
        ),
        ;

        override val locations: List<Location> = locs.toList()
    }
}