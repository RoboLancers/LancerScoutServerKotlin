package com.robolancers.scoutserver.views

import com.robolancers.scoutserver.controllers.TeamController
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.input.ClipboardContent
import javafx.scene.input.DataFormat
import javafx.scene.input.TransferMode
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import javafx.stage.StageStyle
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import tornadofx.*
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Paths

class StrategyView : View(){
    private val stackpaneWidth = 300.0
    private val stackpaneHeight = 100.0

    private val redStackpaneTextID = listOf("strategyRed1", "strategyRed2", "strategyRed3")
    private val blueStackpaneTextID = listOf("strategyBlue1", "strategyBlue2", "strategyBlue3")

    override val root: Parent = vbox {
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS
        alignment = Pos.CENTER

        style {
            paddingAll = 20.0
            spacing = 20.px
        }

        hbox {
            hgrow = Priority.ALWAYS
            vgrow = Priority.ALWAYS
            alignment = Pos.CENTER

            vbox {
                hgrow = Priority.ALWAYS
                vgrow = Priority.ALWAYS
                alignment = Pos.CENTER

                label("Red Alliance") {
                    font = Font(30.0)
                }

                redStackpaneTextID.forEachIndexed { index, stackpaneTextID ->
                    run {
                        hbox {
                            alignment = Pos.CENTER

                            label((index + 1).toString() + ": ")

                            stackpane {
                                prefHeight = stackpaneHeight
                                prefWidth = stackpaneWidth

                                background = Background(BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))

                                border = Border(
                                    BorderStroke(
                                        Color.BLACK,
                                        BorderStrokeStyle.SOLID,
                                        CornerRadii.EMPTY,
                                        BorderWidths.DEFAULT
                                    )
                                )

                                text {
                                    id = stackpaneTextID

                                    fill = Color.WHITE
                                    font = Font.font("System", FontWeight.BOLD, 50.0)
                                }

                                setOnDragOver {
                                    if (it.gestureSource != this@stackpane && it.dragboard.hasString()) {
                                        it.acceptTransferModes(TransferMode.MOVE)
                                    }
                                }

                                setOnDragDropped {
                                    val dragboard = it.dragboard
                                    var success = false

                                    if (dragboard.hasString()) {
                                        val text = scene.lookup("#$stackpaneTextID") as Text
                                        text.text = dragboard.string
                                        success = true
                                    }

                                    it.isDropCompleted = success
                                    it.consume()
                                }
                            }
                        }
                    }
                }
            }

            vbox {
                hgrow = Priority.ALWAYS
                vgrow = Priority.ALWAYS
                alignment = Pos.CENTER

                label("Blue Alliance") {
                    font = Font(30.0)
                }

                blueStackpaneTextID.forEachIndexed { index, stackpaneTextID ->
                    run {
                        hbox {
                            alignment = Pos.CENTER

                            label((index + 1).toString() + ": ")

                            stackpane {
                                prefHeight = stackpaneHeight
                                prefWidth = stackpaneWidth

                                background = Background(BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))

                                border = Border(
                                    BorderStroke(
                                        Color.BLACK,
                                        BorderStrokeStyle.SOLID,
                                        CornerRadii.EMPTY,
                                        BorderWidths.DEFAULT
                                    )
                                )

                                text {
                                    id = stackpaneTextID

                                    fill = Color.WHITE
                                    font = Font.font("System", FontWeight.BOLD, 50.0)
                                }

                                setOnDragOver {
                                    if (it.gestureSource != this@stackpane && it.dragboard.hasString()) {
                                        it.acceptTransferModes(TransferMode.MOVE)
                                    }
                                }

                                setOnDragDropped {
                                    val dragboard = it.dragboard
                                    var success = false

                                    if (dragboard.hasString()) {
                                        val text = scene.lookup("#$stackpaneTextID") as Text
                                        text.text = dragboard.string
                                        success = true
                                    }

                                    it.isDropCompleted = success
                                    it.consume()
                                }
                            }
                        }
                    }
                }
            }
        }

        hbox {
            button("Generate prediction") {
                hgrow = Priority.ALWAYS
                vgrow = Priority.ALWAYS
                alignment = Pos.TOP_CENTER

                onMouseClicked = EventHandler {

                }
            }
        }

        label("Teams") {
            hgrow = Priority.ALWAYS
            vgrow = Priority.ALWAYS
            alignment = Pos.TOP_CENTER
            font = Font(30.0)
        }

        datagrid(TeamController.teams) {
            cellCache {
                stackpane {
                    text(it.teamNumber.toString()){
                        font = Font(50.0)
                    }

                    onDragDetected = EventHandler {mouseEvent ->
                        val dragboard = this@stackpane.startDragAndDrop(TransferMode.MOVE)
                        val clipboardContent = ClipboardContent()
                        clipboardContent.putString(it.teamNumber.toString())
                        dragboard.setContent(clipboardContent)

                        mouseEvent.consume()
                    }
                }
            }
        }
    }

    fun saveToCSV(){
        CSVPrinter(
            Files.newBufferedWriter(Paths.get("./data.csv")),
            CSVFormat.DEFAULT.withHeader(
                "MatchNumber",
                "TeamNumber",
                "RocketCargo",
                "RocketHatch",
                "ShipCargo",
                "ShipHatch"
            )
        ).use { printer ->
            TeamController.teams.forEach { team ->
                team.matches.forEach { match ->
                    printer.printRecord(
                        match.matchNumber,
                        match.teamNumber,
                        match.rocketCargo,
                        match.rocketHatch,
                        match.shipCargo,
                        match.shipHatch
                    )
                }
            }
        }
    }
}