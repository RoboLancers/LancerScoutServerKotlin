package com.robolancers.scoutserver.utilities

import javafx.beans.property.DoublePropertyBase
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.util.converter.NumberStringConverter
import kfoenix.jfxtextfield
import tornadofx.bind
import tornadofx.hbox
import tornadofx.paddingAll
import tornadofx.text

fun Parent.createNumericalEntry(name: String, property: DoublePropertyBase) = hbox {
    paddingAll = 5
    jfxtextfield {
        bind(property, converter = NumberStringConverter())
        prefWidth = 40.0
        minWidth = 40.0
    }
    text("    $name") { alignment = Pos.CENTER_LEFT }
}