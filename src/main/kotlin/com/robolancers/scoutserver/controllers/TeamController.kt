package com.robolancers.scoutserver.controllers

import com.robolancers.scoutserver.models.match.LancerTeam
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList

class TeamController{
    companion object {
        val teams: ObservableList<LancerTeam> = FXCollections.synchronizedObservableList(FXCollections.observableArrayList())
        val teamsFilteredList: FilteredList<LancerTeam> = FilteredList<LancerTeam>(teams)
    }
}