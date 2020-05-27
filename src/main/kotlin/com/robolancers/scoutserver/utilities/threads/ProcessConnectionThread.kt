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
                    //println("Exit")
                    break
                } else {
                    println(data)
                    processCommand(data)
                }
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