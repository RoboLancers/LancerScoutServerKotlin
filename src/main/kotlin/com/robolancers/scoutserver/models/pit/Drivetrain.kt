package com.robolancers.scoutserver.models.pit

enum class Drivetrain(private val drivetrainType: String) {
    FOUR_WHEEL_DRIVE("4WD"),
    SIX_WHEEL_DRIVE("6WD"),
    EIGHT_WHEEL_DRIVE("8WD"),
    SWERVE("Swerve"),
    KIWI("Kiwi"),
    CUSTOM("Custom");

    override fun toString(): String {
        return drivetrainType
    }
}