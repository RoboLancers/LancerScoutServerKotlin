package com.robolancers.scoutserver.models.template

data class Stopwatch(var title: String = "", var time: String = "00:00") : TemplateModel("Stopwatch")