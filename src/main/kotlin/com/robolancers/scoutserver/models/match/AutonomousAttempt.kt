package com.robolancers.scoutserver.models.match

enum class AutonomousAttempt(val attemptName: String){
    NO_CUBE_ATTEMPT ("No cube attempt"),
    ATTEMPTED_SWITCH ("Attempted switch"),
    ATTEMPTED_SCALE ("Attempted scale"),
    SUCCESSFUL_SWITCH ("Successful switch"),
    SUCCESSFUL_SCALE ("Successful scale");
}