package com.robolancers.scoutserver.utilities.threads

import java.io.IOException
import javax.microedition.io.StreamConnection
import java.io.ByteArrayOutputStream
import java.io.InputStream
import com.google.gson.JsonSyntaxException
import com.robolancers.scoutserver.models.match.LancerMatch
import com.robolancers.scoutserver.controllers.TeamController
import com.robolancers.scoutserver.models.match.LancerTeam
import com.robolancers.scoutserver.models.pit.LancerPit
import com.robolancers.scoutserver.utilities.AlertHelper
import javafx.application.Platform
import javafx.scene.control.ButtonType
import org.controlsfx.control.Notifications
import com.google.gson.Gson

class ProcessConnectionThread(private val connection: StreamConnection) : Runnable {
    override fun run() {
        try {
            val inputStream = connection.openInputStream()
            //val outputSteam = connection.openOutputStream()

            while (true) {
                val data = String(readByteArrayCommand(inputStream))

                if (data == "") {
                    println("Exit")
                    break
                }

                println(data)
                processCommand(data)
            }
        } catch (IOException: IOException) {
            IOException.printStackTrace()
            return
        }
    }

    private fun processCommand(data: String) {
        Platform.runLater {
            val dataSplit = data.split("-")

            if(dataSplit.size > 1) {
                val command = dataSplit[0]
                val json = dataSplit[1]
                val gson = Gson()

                try {
                    when (command) {
                        "MATCH" -> {
                            val lancerMatch = gson.fromJson(json, LancerMatch::class.java)
                            val team = TeamController.teams.find { it.teamNumber == lancerMatch.teamNumber }

                            if (team == null) {
                                val newTeam = LancerTeam(lancerMatch.teamNumber)
                                newTeam.matches.add(lancerMatch)

                                TeamController.teams.add(newTeam)
                            } else {
                                if (team.matches.contains(lancerMatch)) {
                                    val duplicate = team.matches.find { it == lancerMatch }

                                    if (duplicate != null) {
                                        val result = AlertHelper.createDuplicateMatchAlert(
                                            duplicate,
                                            lancerMatch
                                        ).showAndWait()

                                        if (result.isPresent && result.get() === ButtonType.OK) {
                                            val index = team.matches.indexOf(lancerMatch)
                                            team.matches.remove(lancerMatch)
                                            team.matches.add(index, lancerMatch)
                                        }
                                    }
                                } else {
                                    team.matches.add(lancerMatch)
                                }
                            }
                        }

                        "PIT" -> {
                            val allDatas = json.split(" ")

                            for (allData in allDatas) {
                                val lancerPit = gson.fromJson(allData, LancerPit::class.java)

                                if(lancerPit != null) {
                                    if (TeamController.teams.any { it.teamNumber == lancerPit.teamNumber }) {
                                        val team = TeamController.teams.find { it.teamNumber == lancerPit.teamNumber }
                                        team?.pitInfo = lancerPit
                                    } else {
                                        val newTeam = LancerTeam(lancerPit.teamNumber)
                                        newTeam.pitInfo = lancerPit

                                        TeamController.teams.add(newTeam)
                                    }
                                }
                            }
                        }

                        else -> Notifications.create().title("Received unknown data").text(json)
                    }
                } catch (e: JsonSyntaxException) {
                    e.printStackTrace()
                }
            }else{
                Notifications.create().title("Received unknown message").text(data)
            }
        }
    }

    @Throws(IOException::class)
    private fun readByteArrayCommand(inputStream: InputStream): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        var read = inputStream.read()

        while (read != -1 && read != 0) {
            byteArrayOutputStream.write(read)
            read = inputStream.read()
        }

        return byteArrayOutputStream.toByteArray()
    }
}