package com.robolancers.scoutserver.models.match

class LancerMatchBuilder {
    private var matchNumber: Int = 0
    private var teamNumber: Int = 0
    private var color: AllianceColor = AllianceColor.BLUE
    private var startingConfiguration: StartingConfiguration = StartingConfiguration.LEVEL_1

    private var crossedAutoLine: Boolean = false
    private var sandstorm: Sandstorm = Sandstorm.AUTONOMOUS

    private var rocketCargo: Int = 0
    private var rocketHatch: Int = 0
    private var shipCargo: Int = 0
    private var shipHatch: Int = 0

    private var endGameAttempt: EndGameAttempt = EndGameAttempt.DID_NOT_CLIMB
    private var brokeDown: Boolean = false
    private var comment: String = ""

    fun setMatchNumber(matchNumber: Int): LancerMatchBuilder {
        this.matchNumber = matchNumber
        return this
    }

    fun setTeamNumber(teamNumber: Int): LancerMatchBuilder {
        this.teamNumber = teamNumber
        return this
    }

    fun setColor(color: AllianceColor): LancerMatchBuilder {
        this.color = color
        return this
    }

    fun setStartingConfiguration(startingConfiguration: StartingConfiguration): LancerMatchBuilder {
        this.startingConfiguration = startingConfiguration
        return this
    }

    fun setCrossedAutoLine(crossedAutoLine: Boolean): LancerMatchBuilder {
        this.crossedAutoLine = crossedAutoLine
        return this
    }

    fun setAutonomousAttempt(sandstorm: Sandstorm): LancerMatchBuilder {
        this.sandstorm = sandstorm
        return this
    }

    fun setRocketCargo(rocketCargo: Int): LancerMatchBuilder {
        this.rocketCargo = rocketCargo
        return this
    }

    fun setRocketHatch(rocketHatch: Int): LancerMatchBuilder {
        this.rocketHatch = rocketHatch
        return this
    }

    fun setShipCargo(shipCargo: Int): LancerMatchBuilder {
        this.shipCargo = shipCargo
        return this
    }

    fun setShipHatch(shipHatch: Int): LancerMatchBuilder {
        this.shipHatch = shipHatch
        return this
    }

    fun setEndGameAttempt(endGameAttempt: EndGameAttempt): LancerMatchBuilder {
        this.endGameAttempt = endGameAttempt
        return this
    }

    fun setBrokeDown(brokeDown: Boolean): LancerMatchBuilder {
        this.brokeDown = brokeDown
        return this
    }

    fun setComment(comments: String): LancerMatchBuilder {
        this.comment = comments
        return this
    }

    fun createLancerMatch(): LancerMatch {
        return LancerMatch(
            matchNumber,
            teamNumber,
            color,
            startingConfiguration,
            crossedAutoLine,
            sandstorm,
            rocketCargo,
            rocketHatch,
            shipCargo,
            shipHatch,
            endGameAttempt,
            brokeDown,
            comment
        )
    }
}