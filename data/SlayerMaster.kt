package org.powbot.krulvis.slayer.data

import org.powbot.api.Tile

object SlayerMaster {

    enum class Konar(
        override val names: List<String>,
        override val optimalStyle: CombatStyle,
        vararg val locs: Location
    ) : Task {
        ABERRANT_SPECTRES(
            listOf("Aberrant spectre"),
            CombatStyle.Melee,
            Location("Catacombs of Kourend", Tile(-1, -1, -1))
        );

        override val locations: List<Location> = locs.toList()
    }
}