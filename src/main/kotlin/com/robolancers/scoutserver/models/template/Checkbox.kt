package com.robolancers.scoutserver.models.template

data class Checkbox(var title: String = "", var checkedState: Boolean = false) : TemplateModel("Checkbox")