package com.robolancers.scoutserver.models.match

import com.robolancers.scoutserver.models.pit.LancerPit
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import java.io.Serializable

class LancerTeam(val teamNumber: Int): Serializable{
    val matches: ObservableList<LancerMatch> = FXCollections.synchronizedObservableList(FXCollections.observableArrayList())
    var pitInfo: LancerPit? = null

    override fun toString(): String {
        return teamNumber.toString()
    }
}