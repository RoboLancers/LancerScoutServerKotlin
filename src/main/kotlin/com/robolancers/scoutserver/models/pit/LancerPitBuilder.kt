package com.robolancers.scoutserver.models.pit

class LancerPitBuilder {
    private var teamNumber: Int = 0
    private var drivetrain: Drivetrain? = null
    private var cubeIntake: CubeIntake? = null
    private var climb: Climb? = null
    private var robotWeight: Int = 0
    private var programmingLanguage: ProgrammingLanguage? = null

    fun setTeamNumber(teamNumber: Int): LancerPitBuilder {
        this.teamNumber = teamNumber
        return this
    }

    fun setDrivetrain(drivetrain: Drivetrain): LancerPitBuilder {
        this.drivetrain = drivetrain
        return this
    }

    fun setCubeIntake(cubeIntake: CubeIntake): LancerPitBuilder {
        this.cubeIntake = cubeIntake
        return this
    }

    fun setClimb(climb: Climb): LancerPitBuilder {
        this.climb = climb
        return this
    }

    fun setRobotWeight(robotWeight: Int): LancerPitBuilder {
        this.robotWeight = robotWeight
        return this
    }

    fun setProgrammingLanguage(programmingLanguage: ProgrammingLanguage): LancerPitBuilder {
        this.programmingLanguage = programmingLanguage
        return this
    }

    fun createLancerPit(): LancerPit {
        return LancerPit(teamNumber, drivetrain!!, cubeIntake!!, climb!!, robotWeight, programmingLanguage!!)
    }
}