package com.robolancers.scoutserver.models.pit

enum class ProgrammingLanguage(private val programmingLanguageType: String) {
    JAVA("Java"),
    C_PLUS_PLUS("C++"),
    LABVIEW("Labview"),
    OTHER("Other");

    override fun toString(): String {
        return programmingLanguageType
    }
}