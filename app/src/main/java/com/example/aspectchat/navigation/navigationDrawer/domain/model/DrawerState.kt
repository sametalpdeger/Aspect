package com.example.aspectchat.navigation.navigationDrawer.domain.model

enum class IsDrawerOpenState {
    Opened,
    Closed
}

fun IsDrawerOpenState.isOpened(): Boolean {
    return this.name == "Opened"
}


fun IsDrawerOpenState.opposite(): IsDrawerOpenState {
    return if (this == IsDrawerOpenState.Opened) IsDrawerOpenState.Closed
    else IsDrawerOpenState.Opened
}

data class UserData(
    val ID: String,
    val name: String,
    val status: String,
    val image: Int,
)
