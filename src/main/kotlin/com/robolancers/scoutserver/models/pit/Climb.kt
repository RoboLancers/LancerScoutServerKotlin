package com.robolancers.scoutserver.models.pit

enum class Climb constructor(private val climbType: String) {
    LEVEL_1("Level 1"),
    LEVEL_2("Level 2");

    override fun toString(): String {
        return climbType
    }
}