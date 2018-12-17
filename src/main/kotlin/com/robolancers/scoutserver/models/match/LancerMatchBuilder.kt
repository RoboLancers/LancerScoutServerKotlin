package com.robolancers.scoutserver.models.match

class LancerMatchBuilder {
    private var matchNumber: Int = 0
    private var teamNumber: Int = 0
    private var color: AllianceColor = AllianceColor.BLUE
    private var startingConfiguration: StartingConfiguration = StartingConfiguration.LEFT
    private var crossedAutoLine: Boolean = false
    private var autonomousAttempt: AutonomousAttempt = AutonomousAttempt.NO_CUBE_ATTEMPT
    private var wrongSideAuto: Boolean = false
    private var allianceSwitch: Int = 0
    private var centerScale: Int = 0
    private var opponentSwitch: Int = 0
    private var exchange: Int = 0
    private var endGameAttempt: EndGameAttempt = EndGameAttempt.NOT_PARKED_ON_PLATFORM
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

    fun setAutonomousAttempt(autonomousAttempt: AutonomousAttempt): LancerMatchBuilder {
        this.autonomousAttempt = autonomousAttempt
        return this
    }

    fun setWrongSideAuto(wrongSideAuto: Boolean): LancerMatchBuilder {
        this.wrongSideAuto = wrongSideAuto
        return this
    }

    fun setAllianceSwitch(allianceSwitch: Int): LancerMatchBuilder {
        this.allianceSwitch = allianceSwitch
        return this
    }

    fun setCenterScale(centerScale: Int): LancerMatchBuilder {
        this.centerScale = centerScale
        return this
    }

    fun setOpponentSwitch(opponentSwitch: Int): LancerMatchBuilder {
        this.opponentSwitch = opponentSwitch
        return this
    }

    fun setExchange(exchange: Int): LancerMatchBuilder {
        this.exchange = exchange
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
            autonomousAttempt,
            wrongSideAuto,
            allianceSwitch,
            centerScale,
            opponentSwitch,
            exchange,
            endGameAttempt,
            brokeDown,
            comment
        )
    }
}