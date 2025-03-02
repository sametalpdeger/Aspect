package com.example.aspectchat.core.helpers

fun envNotFound(env: String): Nothing =
    throw IllegalStateException("$env environment variable is not set")

object Env {
    val mainServerUrl = System.getenv("MAIN_SERVER_URL")
        ?: envNotFound("MAIN_SERVER_URL")

    val environment = System.getenv("ENVIRONMENT")
        ?: envNotFound("ENVIRONMENT")
}