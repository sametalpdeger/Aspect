package com.example.aspectchat.screens.home.data.model

data class Message(
    val id: String,
    val userId: String,
    val isMyMessage: Boolean,
    val message: String,
    val time: Long,
)

enum class MessageSortType {
    NEW,
    OLD,
}