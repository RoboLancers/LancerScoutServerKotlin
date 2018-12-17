package com.robolancers.scoutserver.models.pit

data class LancerPit(
    val teamNumber: Int,
    val drivetrain: Drivetrain,
    val cubeIntake: CubeIntake,
    val climb: Climb,
    val robotWeight: Int,
    val programmingLanguage: ProgrammingLanguage
)