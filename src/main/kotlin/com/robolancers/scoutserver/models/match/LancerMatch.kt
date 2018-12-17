package com.robolancers.scoutserver.models.match

import java.util.Objects

class LancerMatch(
    val matchNumber: Int,
    val teamNumber: Int,
    val color: AllianceColor,
    val startingConfiguration: StartingConfiguration,
    val crossedAutoLine: Boolean,
    val autonomousAttempt: AutonomousAttempt,
    val wrongSideAuto: Boolean,
    val allianceSwitch: Int,
    val centerScale: Int,
    val opponentSwitch: Int,
    val exchange: Int,
    val endGameAttempt: EndGameAttempt,
    val robotBrokeDown: Boolean,
    val comment: String
) {

    override fun toString(): String {
        return "Match $matchNumber"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        return if (this.javaClass != other.javaClass) {
            false
        } else this.matchNumber == (other as LancerMatch).matchNumber

    }

    override fun hashCode(): Int {
        return Objects.hash(
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
            robotBrokeDown,
            comment
        )
    }

    fun getMatchInfo(): String {
        val matchInfo = StringBuilder()

        matchInfo.append("Alliance Color: ")
        matchInfo.append(color)
        matchInfo.append("\n")
        matchInfo.append("Starting Configuration: ")
        matchInfo.append(startingConfiguration)
        matchInfo.append("\n")

        matchInfo.append("\nAutonomous\n")
        matchInfo.append("Crossed auto line: ")
        matchInfo.append(crossedAutoLine)
        matchInfo.append("\n")
        matchInfo.append("Autonomous Attempt: ")
        matchInfo.append(autonomousAttempt)
        matchInfo.append("\n")
        matchInfo.append("Put cube on wrong side? ")
        matchInfo.append(if (wrongSideAuto) "Yes" else "No")
        matchInfo.append("\n")

        matchInfo.append("\nTeleOp\n")
        matchInfo.append("Alliance Switch: ")
        matchInfo.append(allianceSwitch)
        matchInfo.append("\n")
        matchInfo.append("Center Scale: ")
        matchInfo.append(centerScale)
        matchInfo.append("\n")
        matchInfo.append("Opponent Switch: ")
        matchInfo.append(opponentSwitch)
        matchInfo.append("\n")
        matchInfo.append("Exchange: ")
        matchInfo.append(exchange)
        matchInfo.append("\n")

        matchInfo.append("\nEnd Game\n")
        matchInfo.append("End Game Attempt: ")
        matchInfo.append(endGameAttempt)
        matchInfo.append("\n")
        matchInfo.append("Did robot break down? ")
        matchInfo.append(if (robotBrokeDown) "Yes" else "No")
        matchInfo.append("\n")

        matchInfo.append("Other Comments: ")
        matchInfo.append(comment)
        matchInfo.append("\n")

        return matchInfo.toString()
    }
}