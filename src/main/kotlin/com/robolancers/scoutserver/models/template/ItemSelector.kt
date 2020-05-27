package com.robolancers.scoutserver.models.template

data class ItemSelector(var title: String = "", var list: MutableList<ItemSelectorItem> = mutableListOf()) : TemplateModel("ItemSelector")