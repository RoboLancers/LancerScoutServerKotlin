package com.robolancers.scoutserver

import com.robolancers.scoutserver.utilities.threads.WaitForDevicesThread
import com.robolancers.scoutserver.views.AllView
import tornadofx.App
import tornadofx.launch

class Main : App(AllView::class) {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Thread(WaitForDevicesThread).start()
            launch<Main>(args)
        }
    }
}