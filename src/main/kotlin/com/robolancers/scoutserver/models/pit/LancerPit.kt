package com.robolancers.scoutserver.models.pit

data class LancerPit(
    val teamNumber: Int,
    val drivetrain: Drivetrain,
    val cargoIntake: Intake,
    val hatchIntake: Intake,
    val climb: Climb,
    val robotWeight: Int,
    val programmingLanguage: ProgrammingLanguage
)