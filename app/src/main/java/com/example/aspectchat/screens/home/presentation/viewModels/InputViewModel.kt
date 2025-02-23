package com.example.aspectchat.screens.home.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {
    private val _input = mutableStateOf("")
    val input = _input

    fun setInput(input: String) {
        _input.value = input
    }

    fun clearInput() {
        _input.value = ""
    }

    fun sendInput() {
        // TODO: Send input
    }

    fun sendInputWithDelay() {
        // TODO: Send input with delay
    }

    
}