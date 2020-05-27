package com.robolancers.scoutserver.models.template

data class Note(var title: String = "", var text: String = "") : TemplateModel("Note")