package com.robolancers.scoutserver.views

import com.robolancers.scoutserver.controllers.TeamController
import com.robolancers.scoutserver.models.match.*
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.scene.layout.Priority
import javafx.scene.text.Font
import kfoenix.jfxlistview
import kfoenix.jfxtextfield
import tornadofx.*
import java.lang.NumberFormatException
import kotlin.random.Random

class MainView(tabPane: TabPane): View(){

    private var currentTeamNum = 0

    override val root = hbox {
        stylesheets += resources["/MainViewStyle.css"]

        vbox {
            style {
                paddingAll = 20.0
                spacing = 5.px
            }

            hgrow = Priority.ALWAYS
            alignment = Pos.CENTER

            vbox {
                paddingAll = 25

                jfxtextfield {
                    paddingAll = 5
                    prefWidth = 40.0

                    font = Font(30.0)

                    isLabelFloat = true
                    promptText = "Enter team number to search for"
                    id = "teamNumberSearchTextField"

                    onAction = EventHandler {
                        val errorLabel = scene.lookup("#errorLabel") as Label

                        try {
                            val teamNumber = text.toInt()
                            val team = TeamController.teams.find { lancerTeam -> lancerTeam.teamNumber == teamNumber }

                            if (team == null) {
                                errorLabel.text = "Team $teamNumber not found!"
                            } else {
                                TeamView.teamProperty.set(team)
                                tabPane.selectionModel.select(1)

                                clear()
                                errorLabel.text = ""
                            }
                        } catch (numberFormatException: NumberFormatException) {
                            errorLabel.text = "Enter a valid number!"
                        }
                    }

                    textProperty().addListener {
                        _, _, newValue ->
                        if (!newValue.matches(Regex("\\d*"))){
                            text = newValue.replace(Regex("[^\\d]"), "")
                        }

                        TeamController.teamsFilteredList.setPredicate {
                            if(!newValue.isNullOrBlank()) {
                                it.teamNumber.toString().contains(newValue)
                            }else{
                                true
                            }
                        }
                    }
                }
            }

            label {
                id = "errorLabel"
            }

            button {
                text = "Click me"
                onMouseClicked = EventHandler {
                    val newTeam = LancerTeam(++currentTeamNum)

                    for(i in 0..5){
                        newTeam.matches.add(LancerMatchBuilder().setMatchNumber(i).setTeamNumber(currentTeamNum).setAllianceSwitch(Random.nextInt(10)).setCenterScale(Random.nextInt(10)).setOpponentSwitch(Random.nextInt(10)).setExchange(Random.nextInt(10)).createLancerMatch())
                    }

                    TeamController.teams.add(newTeam)
                }
            }
        }

        vbox{
            paddingAll = 5
            vgrow = Priority.ALWAYS
            hgrow = Priority.ALWAYS
            alignment = Pos.CENTER

            label("Teams") {
                font = Font(25.0)
            }

            jfxlistview(TeamController.teamsFilteredList) {
                id = "teamList"
                hgrow = Priority.ALWAYS
                vgrow = Priority.ALWAYS

                onDoubleClick {
                    TeamView.teamProperty.set(this.selectionModel.selectedItem)
                    tabPane.selectionModel.select(1)

                    val errorLabel = scene.lookup("#errorLabel") as Label
                    errorLabel.text = ""
                }

                setOnKeyPressed {
                    if(it.code == KeyCode.DELETE){
                        val selectedIndex = selectionModel.selectedIndex

                        if(selectedIndex != -1){
                            val alert = Alert(Alert.AlertType.CONFIRMATION)
                            alert.title = "Delete"
                            alert.headerText = null
                            alert.contentText = "Are you sure you want to delete?"

                            val result = alert.showAndWait()

                            if(result.isPresent && result.get() == ButtonType.OK){
                                TeamController.teams.remove(selectionModel.selectedItem)
                                selectionModel.clearSelection()
                            }else{
                                alert.close()
                            }
                        }
                    }
                }

                contextmenu {
                    item("Delete").action {
                        apply {
                            val alert = Alert(Alert.AlertType.CONFIRMATION)
                            alert.title = "Delete team " + selectedItem?.teamNumber
                            alert.headerText = "Are you sure you want to delete team " + selectedItem?.teamNumber + "?"
                            alert.contentText = null

                            val result = alert.showAndWait()

                            if(result.isPresent && result.get() == ButtonType.OK){
                                TeamController.teams.remove(selectedItem)
                                selectionModel.clearSelection()
                            }else{
                                alert.close()
                            }
                        }
                    }
                }
            }.cellFormat {
                text = it.toString()
                font = Font(30.0)
                alignment = Pos.CENTER
            }
        }
    }
}