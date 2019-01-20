package com.robolancers.scoutserver.models.match

enum class EndGameAttempt(val attemptName: String){
    DID_NOT_CLIMB("Did not climb"),
    LEVEL_1("Climbed to level 1"),
    LEVEL_2("Climbed to level 2"),
    LEVEL_3("Climbed to level 3");
}