package com.robolancers.scoutserver.utilities.threads

import java.lang.Exception
import javax.bluetooth.DiscoveryAgent
import javax.bluetooth.LocalDevice
import javax.bluetooth.UUID
import javax.microedition.io.Connector
import javax.microedition.io.StreamConnectionNotifier

object WaitForDevicesThread : Runnable {
    override fun run() {
        try {
            val localDevice = LocalDevice.getLocalDevice()
            localDevice.discoverable = DiscoveryAgent.GIAC

            val uuid = UUID("fba199f747a74ed4b8803073424d2e2c", false)
            val url = "btspp://localhost:" + uuid.toString() + ";name=FRCScouting"
            val notifier: StreamConnectionNotifier = Connector.open(url) as StreamConnectionNotifier

            while (true) {
                System.out.println("Waiting for connection... so lonely")
                val connection = notifier.acceptAndOpen()

                Thread(ProcessConnectionThread(connection)).start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }
}