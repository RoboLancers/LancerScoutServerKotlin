package com.robolancers.scoutserver.views

import com.robolancers.scoutserver.utilities.AlertHelper
import javafx.application.Platform
import javafx.scene.Parent
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.TabPane
import kfoenix.jfxtabpane
import tornadofx.View
import tornadofx.plusAssign
import tornadofx.tab
import java.util.*

class AllView : View("Scouting Server") {
    override val root: Parent = jfxtabpane {
        stylesheets += resources["/AppStyle.css"]

        primaryStage.setOnCloseRequest {
            val result: Optional<ButtonType>  = AlertHelper.createAlert(Alert.AlertType.CONFIRMATION, scene.window, "Confirm Close", "Confirm close? All data will be lost!").showAndWait()

            if(result.isPresent && result.get() == ButtonType.OK){
                Platform.exit()
                System.exit(0)
            }else{
                it.consume()
            }
        }

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        prefHeight = 800.0
        prefWidth = 1200.0

        tab("Main") {
            this += MainView(this@jfxtabpane)
        }

        tab("Team"){
            this += TeamView()
        }

        tab("Strategy"){
            this += StrategyView()
        }
    }
}