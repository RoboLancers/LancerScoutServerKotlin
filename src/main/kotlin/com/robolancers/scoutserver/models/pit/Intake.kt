package com.robolancers.scoutserver.models.pit

enum class Intake(private val cubeIntakeType: String) {
    FLOOR_INTAKE("Floor Intake"),
    HUMAN_INTAKE("Portal/Exchange Intake"),
    BOTH_INTAKES("Both Intakes"),
    NONE_INTAKE("None");

    override fun toString(): String {
        return cubeIntakeType
    }
}