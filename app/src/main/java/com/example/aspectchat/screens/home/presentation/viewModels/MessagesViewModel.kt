package com.example.aspectchat.screens.home.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.aspectchat.screens.home.data.model.Message
import com.example.aspectchat.screens.home.data.model.MessageSortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID.randomUUID

class MessagesViewModel : ViewModel() {
    val ID1 = randomUUID().toString()
    val ID2 = randomUUID().toString()

    private val _messages = MutableStateFlow(
        listOf(
            Message(randomUUID().toString(), ID1, true, "hey", 0),
            Message(randomUUID().toString(), ID2, false, "hi! how's it going?", 0),
            Message(
                randomUUID().toString(),
                ID1,
                true,
                "tired af, been debugging all morning 😫",
                0
            ),
            Message(randomUUID().toString(), ID2, false, "oof", 0),
            Message(randomUUID().toString(), ID1, true, "the prod server crashed again", 0),
            Message(randomUUID().toString(), ID2, false, "seriously? what happened this time?", 0),
            Message(randomUUID().toString(), ID1, true, "memory leak 🤦‍♂️", 0),
            Message(randomUUID().toString(), ID2, false, "rip", 0),
            Message(randomUUID().toString(), ID1, true, "took me 3 hours to find it", 0),
            Message(randomUUID().toString(), ID2, false, "need help?", 0),
            Message(randomUUID().toString(), ID1, true, "nah", 0),
            Message(randomUUID().toString(), ID2, false, "you sure? i can take a look", 0),
            Message(randomUUID().toString(), ID1, true, "fixed it already, just need coffee ☕", 0),
            Message(randomUUID().toString(), ID2, false, "lol", 0),
            Message(randomUUID().toString(), ID1, true, "wanna grab some?", 0),
            Message(randomUUID().toString(), ID2, false, "sure! starbucks in 10?", 0),
            Message(randomUUID().toString(), ID1, true, "omw", 0),
            Message(randomUUID().toString(), ID2, false, "👍", 0)
        )
    )
    val messages = _messages.asStateFlow()

    fun sortMessage(
        type: MessageSortType,
    ) {

    }


}