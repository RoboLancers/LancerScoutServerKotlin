package com.robolancers.scoutserver.models.pit

class LancerPitBuilder {
    private var teamNumber: Int = 0
    private var drivetrain: Drivetrain? = null

    private var cargoIntake: Intake? = null
    private var hatchIntake: Intake? = null
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

    fun setCargoIntake(intake: Intake): LancerPitBuilder {
        this.cargoIntake = intake
        return this
    }

    fun setHatchIntake(intake: Intake): LancerPitBuilder {
        this.hatchIntake = intake
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
        return LancerPit(teamNumber, drivetrain!!, cargoIntake!!, hatchIntake!!, climb!!, robotWeight, programmingLanguage!!)
    }
}