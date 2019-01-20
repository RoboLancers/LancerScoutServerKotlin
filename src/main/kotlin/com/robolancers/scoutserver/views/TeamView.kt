package com.robolancers.scoutserver.views

import com.robolancers.scoutserver.controllers.TeamController
import com.robolancers.scoutserver.models.match.LancerTeam
import com.robolancers.scoutserver.utilities.AlertHelper
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.chart.*
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Priority
import javafx.scene.text.Font
import tornadofx.*
import java.text.NumberFormat
import kotlin.math.truncate

@Suppress("UNCHECKED_CAST")
class TeamView : View() {
    private val teamNumberLabel
        get() = root.scene.lookup("#teamNumberLabel") as Label

    private val drivetrainLabel
        get() = root.scene.lookup("#drivetrainLabel") as Label

    private val cargoIntakeLabel
        get() = root.scene.lookup("#cargoIntakeLabel") as Label

    private val hatchIntakeLabel
        get() = root.scene.lookup("#cargoIntakeLabel") as Label

    private val climbLabel
        get() = root.scene.lookup("#climbLabel") as Label

    private val robotWeightLabel
        get() = root.scene.lookup("#robotWeightLabel") as Label

    private val programmingLanguageLabel
        get() = root.scene.lookup("#programmingLanguageLabel") as Label

    private val totalMatchesLabel
        get() = root.scene.lookup("#totalMatchesLabel") as Label

    private val rocketCargoLabel
        get() = root.scene.lookup("#rocketCargoLabel") as Label

    private val rocketHatchLabel
        get() = root.scene.lookup("#rocketHatchLabel") as Label

    private val shipCargoLabel
        get() = root.scene.lookup("#shipCargoLabel") as Label

    private val shipHatchLabel
        get() = root.scene.lookup("#shipHatchLabel") as Label

    private val robotBrokeDownLabel
        get() = root.scene.lookup("#robotBrokeDownLabel") as Label

    private val crossAutoLineLabel
        get() = root.scene.lookup("#crossAutoLineLabel") as Label

    private val teamMatchesSqueezeBox
        get() = root.scene.lookup("#teamMatchesSqueezeBox") as SqueezeBox

    private val numberFormat = NumberFormat.getPercentInstance()

    private val chartXAxis = CategoryAxis()
    private val chartYAxis = NumberAxis()

    private val teamSummaryChart
        get() = root.scene.lookup("#teamSummaryChart") as StackedBarChart<String, Number>

    override val root: Parent = hbox {
        stylesheets += resources["/TeamViewStyle.css"]

        style {
            paddingAll = 20.0
            spacing = 20.px
        }

        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS
        alignment = Pos.CENTER

        vbox {
            vgrow = Priority.ALWAYS
            hgrow = Priority.ALWAYS
            alignment = Pos.CENTER

            hbox {
                alignment = Pos.BOTTOM_CENTER

                label {
                    text = "Team View"
                    font = Font(75.0)
                    id = "teamNumberLabel"
                }
            }

            hbox {
                alignment = Pos.CENTER

                vbox {
                    hgrow = Priority.ALWAYS
                    alignment = Pos.CENTER_LEFT

                    hbox {
                        alignment = Pos.CENTER

                        label("Pit Information") {
                            font = Font(25.0)
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Drivetrain: ")
                        label {
                            id = "drivetrainLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Cargo Intake: ")
                        label {
                            id = "cargoIntakeLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Hatch Intake: ")
                        label {
                            id = "hatchIntakeLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Climb: ")
                        label {
                            id = "climbLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Robot Weight: ")
                        label {
                            id = "robotWeightLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Programming Language: ")
                        label {
                            id = "programmingLanguageLabel"
                        }
                    }
                }

                vbox {
                    hgrow = Priority.ALWAYS
                    alignment = Pos.CENTER_RIGHT

                    hbox {
                        alignment = Pos.CENTER

                        label("Match Summary") {
                            font = Font(25.0)
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Total Matches: ")
                        label {
                            text = "N/A"
                            id = "totalMatchesLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Rocket Cargo: ")
                        label {
                            text = "N/A"
                            id = "rocketCargoLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Rocket Hatch: ")
                        label {
                            text = "N/A"
                            id = "rocketHatchLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Ship Cargo: ")
                        label {
                            text = "N/A"
                            id = "shipCargoLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Ship Hatch: ")
                        label {
                            text = "N/A"
                            id = "shipHatchLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Cross Auto Line: ")
                        label {
                            text = "N/A"
                            id = "crossAutoLineLabel"
                        }
                    }

                    hbox {
                        alignment = Pos.CENTER

                        label("Robot Broke Down: ")
                        label {
                            text = "N/A"
                            id = "robotBrokeDownLabel"
                        }
                    }
                }
            }

            scrollpane(fitToHeight = true, fitToWidth = true) {
                hgrow = Priority.ALWAYS
                vgrow = Priority.ALWAYS

                stackedbarchart("Team Summary", chartXAxis, chartYAxis){
                    id = "teamSummaryChart"
                }
            }
        }

        vbox {
            hbox {
                hgrow = Priority.ALWAYS
                alignment = Pos.CENTER

                label("Matches") {
                    font = Font(25.0)
                }
            }

            scrollpane {
                prefViewportWidth = 400.0
                hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER

                squeezebox(multiselect = true, fillHeight = false) {
                    id = "teamMatchesSqueezeBox"
                    fitToWidth(this@scrollpane)
                }
            }
        }
    }

    init {
        teamProperty.addListener { _, _, newValue ->
            teamMatchesSqueezeBox.getChildList()?.clear()

            currentTeam = TeamController.teams.find { it == newValue }

            val currentTeamPitInfo = currentTeam?.pitInfo
            val matches = currentTeam?.matches
            val totalMatches = matches?.size ?: 0

            teamNumberLabel.text = "Team $currentTeam"

            drivetrainLabel.text = currentTeamPitInfo?.drivetrain?.toString() ?: "No information"
            cargoIntakeLabel.text = currentTeamPitInfo?.cargoIntake?.toString() ?: "No information"
            hatchIntakeLabel.text = currentTeamPitInfo?.hatchIntake?.toString() ?: "No information"
            climbLabel.text = currentTeamPitInfo?.climb?.toString() ?: "No information"
            robotWeightLabel.text = currentTeamPitInfo?.robotWeight?.toString() ?: "No information"
            programmingLanguageLabel.text = currentTeamPitInfo?.programmingLanguage?.toString() ?: "No information"

            totalMatchesLabel.text = matches?.size?.toString() ?: "No information"

            rocketCargoLabel.text =
                    fixNaN(truncate(matches?.sumBy { it.rocketCargo }?.toDouble()?.div(totalMatches) ?: 0.0).toString())
            rocketHatchLabel.text =
                    fixNaN(truncate(matches?.sumBy { it.rocketHatch }?.toDouble()?.div(totalMatches) ?: 0.0).toString())
            shipCargoLabel.text =
                    fixNaN(truncate(matches?.sumBy { it.shipCargo }?.toDouble()?.div(totalMatches) ?: 0.0).toString())
            shipHatchLabel.text =
                    fixNaN(truncate(matches?.sumBy { it.shipHatch }?.toDouble()?.div(totalMatches) ?: 0.0).toString())
            crossAutoLineLabel.text =
                    fixNaN(numberFormat.format(matches?.sumBy { if (it.crossedAutoLine) 1 else 0 }?.toDouble()?.div(totalMatches) ?: 0.0).toString())
            robotBrokeDownLabel.text =
                    fixNaN(numberFormat.format(matches?.sumBy { if (it.robotBrokeDown) 1 else 0 }?.toDouble()?.div(totalMatches) ?: 0.0).toString())

            val rocketCargoSeries = XYChart.Series<String, Number>()
            rocketCargoSeries.name = "Rocket Cargo"

            val rocketHatchSeries = XYChart.Series<String, Number>()
            rocketHatchSeries.name = "Rocket Hatch"

            val shipCargoSeries = XYChart.Series<String, Number>()
            shipCargoSeries.name = "Ship Cargo"

            val shipHatchSeries = XYChart.Series<String, Number>()
            shipHatchSeries.name = "Ship Hatch"

            val categories = FXCollections.observableArrayList<String>()

            if (matches != null) {
                for (match in matches) {
                    teamMatchesSqueezeBox.fold("Match " + match.matchNumber.toString()) {
                        vbox {
                            id = "squeezeBoxFold"

                            label("Alliance Color: " + match.color)
                            label("Starting Configuration: " + match.startingConfiguration.startingConfigurationName)

                            label("Autonomous") {
                                font = Font(25.0)
                            }

                            label("Crossed Auto Line: " + match.crossedAutoLine)
                            label("Sandstorm: " + match.sandstorm.sandstormName)

                            label("Scoring") {
                                font = Font(25.0)
                            }

                            label("Rocket Cargo: " + match.rocketCargo)
                            label("Rocket Hatch: " + match.rocketHatch)
                            label("Ship Cargo: " + match.shipCargo)
                            label("Ship Hatch: " + match.shipHatch)

                            label("End Game") {
                                font = Font(25.0)
                            }

                            label("End Game Attempt: " + match.endGameAttempt.attemptName)
                            label("Robot broke down: " + match.robotBrokeDown)

                            label("Comments") {
                                font = Font(25.0)
                            }

                            label("Comment: " + match.comment)
                        }

                        contextmenu {
                            item("Show match " + match.matchNumber + " another window").action {
                                apply {
                                    AlertHelper.showMatchAlert(match)
                                }
                            }
                        }
                    }

                    rocketCargoSeries.data.add(XYChart.Data(match.matchNumber.toString(), match.rocketCargo))
                    rocketHatchSeries.data.add(XYChart.Data(match.matchNumber.toString(), match.rocketHatch))
                    shipCargoSeries.data.add(XYChart.Data(match.matchNumber.toString(), match.shipCargo))
                    shipHatchSeries.data.add(XYChart.Data(match.matchNumber.toString(), match.shipHatch))

                    categories.add(match.matchNumber.toString())
                }
            }

            chartXAxis.categories = categories
            teamSummaryChart.data = FXCollections.observableArrayList(rocketCargoSeries, rocketHatchSeries, shipCargoSeries, shipHatchSeries)
        }

        chartXAxis.label = "Match Number"

        chartYAxis.tickUnit = 1.0
        chartYAxis.label = "Number of Cargo/Hatches"
    }

    private fun fixNaN(percentage: String): String {
        return if (percentage == "NaN") "No information" else percentage
    }

    companion object {
        var teamProperty = SimpleObjectProperty<LancerTeam>(LancerTeam(0))
        var currentTeam: LancerTeam? = null
    }
}