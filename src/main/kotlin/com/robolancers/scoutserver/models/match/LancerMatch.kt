package com.robolancers.scoutserver.models.match

import java.util.Objects

class LancerMatch(
    val matchNumber: Int,
    val teamNumber: Int,
    val color: AllianceColor,
    val startingConfiguration: StartingConfiguration,
    val crossedAutoLine: Boolean,
    val sandstorm: Sandstorm,
    val rocketCargo: Int,
    val rocketHatch: Int,
    val shipCargo: Int,
    val shipHatch: Int,
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
            sandstorm,
            rocketCargo,
            rocketHatch,
            shipCargo,
            shipHatch,
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
        matchInfo.append(sandstorm)
        matchInfo.append("\n")
        matchInfo.append("Put cube on wrong side? ")

        matchInfo.append("\nScoring\n")
        matchInfo.append("Rocket Cargo: ")
        matchInfo.append(rocketCargo)
        matchInfo.append("\n")
        matchInfo.append("Rocket Hatch: ")
        matchInfo.append(rocketHatch)
        matchInfo.append("\n")
        matchInfo.append("Ship Cargo: ")
        matchInfo.append(shipCargo)
        matchInfo.append("\n")
        matchInfo.append("Ship Hatch: ")
        matchInfo.append(shipHatch)
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