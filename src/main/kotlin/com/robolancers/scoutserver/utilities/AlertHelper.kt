package com.robolancers.scoutserver.utilities

import javafx.scene.layout.HBox
import javafx.scene.control.Alert
import com.robolancers.scoutserver.models.match.LancerMatch
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.stage.Modality
import javafx.stage.Window

@Suppress("unused", "MemberVisibilityCanBePrivate")
object AlertHelper {
    fun showAlert(alertType: Alert.AlertType, owner: Window, title: String, message: String): Alert {
        val alert = createAlert(alertType, owner, title, message)
        alert.show()
        return alert
    }

    fun createAlert(alertType: Alert.AlertType, owner: Window, title: String, message: String): Alert {
        val alert = Alert(alertType)
        alert.title = title
        alert.headerText = null
        alert.contentText = message
        alert.initOwner(owner)

        return alert
    }

    fun showMatchAlert(match: LancerMatch, owner: Window? = null) {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.initModality(Modality.NONE)
        alert.title = "Match Information"
        alert.headerText = "Match " + match.matchNumber + " for team " + match.teamNumber
        alert.contentText = match.getMatchInfo()
        alert.initOwner(owner)
        alert.show()
    }

    fun createDuplicateMatchAlert(match1: LancerMatch, match2: LancerMatch, owner: Window? = null): Alert {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.initModality(Modality.APPLICATION_MODAL)
        alert.title = "Match Conflict"
        alert.headerText = "Overwrite Match " + match1.matchNumber + " for team " + match1.teamNumber + "?"

        var match1Difference = ""
        var match2Difference = ""

        val dialogPaneContent = HBox()
        val match1Label = Label()
        val match2Label = Label()

        if (match1.color !== match2.color) {
            match1Difference += "Color: " + match1.color + "\n"
            match2Difference += "Color: " + match2.color + "\n"
        }

        if (match1.startingConfiguration !== match2.startingConfiguration) {
            match1Difference += "Starting Configuration: " + match1.startingConfiguration + "\n"
            match2Difference += "Starting Configuration: " + match2.startingConfiguration + "\n"
        }

        if (match1.crossedAutoLine != match2.crossedAutoLine) {
            match1Difference += "Crossed Auto Line: " + match1.crossedAutoLine + "\n"
            match2Difference += "Crossed Auto Line: " + match2.crossedAutoLine + "\n"
        }

        if (match1.sandstorm !== match2.sandstorm) {
            match1Difference += "Autonomous Attempt: " + match1.sandstorm + "\n"
            match2Difference += "Autonomous Attempt: " + match2.sandstorm + "\n"
        }

        if (match1.rocketCargo != match2.rocketCargo) {
            match1Difference += "Rocket Cargo: " + match1.rocketCargo + "\n"
            match2Difference += "Rocket Cargo: " + match2.rocketCargo + "\n"
        }

        if (match1.rocketHatch != match2.rocketHatch) {
            match1Difference += "Rocket Hatch: " + match1.rocketHatch + "\n"
            match2Difference += "Rocket Hatch: " + match2.rocketHatch + "\n"
        }

        if (match1.shipCargo != match2.shipCargo) {
            match1Difference += "Ship Cargo: " + match1.shipCargo + "\n"
            match2Difference += "Ship Cargo: " + match2.shipCargo + "\n"
        }

        if (match1.shipHatch != match2.shipHatch) {
            match1Difference += "Ship Hatch: " + match1.shipHatch + "\n"
            match2Difference += "Ship Hatch: " + match2.shipHatch + "\n"
        }

        if (match1.endGameAttempt !== match2.endGameAttempt) {
            match1Difference += "End Game Attempt: " + match1.endGameAttempt + "\n"
            match2Difference += "End Game Attempt: " + match2.endGameAttempt + "\n"
        }

        if (match1.robotBrokeDown != match2.robotBrokeDown) {
            match1Difference += "Robot Broke Down: " + match1.robotBrokeDown + "\n"
            match2Difference += "Robot Broke Down: " + match2.robotBrokeDown + "\n"
        }

        if (match1.comment != match2.comment) {
            match1Difference += "Comments: " + match1.comment + "\n"
            match2Difference += "Comments: " + match2.comment + "\n"
        }

        if (match1Difference.isEmpty() && match2Difference.isEmpty()) {
            match1Label.text = "Everything is the same"
            match1Label.padding = Insets(20.0)

            dialogPaneContent.children.addAll(match1Label)
        } else {
            match1Label.text = match1Difference
            match2Label.text = match2Difference

            match1Label.padding = Insets(20.0)
            match2Label.padding = Insets(20.0)

            dialogPaneContent.children.addAll(match1Label, match2Label)
        }

        alert.dialogPane.content = dialogPaneContent

        alert.initOwner(owner)

        return alert
    }
}