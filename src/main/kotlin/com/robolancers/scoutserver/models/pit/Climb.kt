package com.robolancers.scoutserver.models.pit

enum class Climb constructor(private val climbType: String) {
    SOLO_CLIMB("Solo Climb"),
    CLIMBER_WITH_RAMP_1("Climber with ramp for one robot"),
    CLIMBER_WITH_RAMP_2("Climber with ramp for two robots"),
    RAMP_1("Ramp for one robot"),
    RAMP_2("Ramp for two robots"),
    NONE_INTAKE("None");

    override fun toString(): String {
        return climbType
    }
}