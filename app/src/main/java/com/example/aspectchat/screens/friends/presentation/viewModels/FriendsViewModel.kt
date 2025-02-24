package com.example.aspectchat.screens.friends.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.aspectchat.R
import kotlinx.coroutines.flow.MutableStateFlow

data class Friend(
    val id: String,
    val name: String,
    val image: Int,
)

class FriendsViewModel : ViewModel() {
    private val _friends = MutableStateFlow(
        listOf(
            Friend("1", "John Doe", R.drawable.image),
            Friend("2", "Jane Doe", R.drawable.image2),
            Friend("3", "John Smith", R.drawable.image3),
            Friend("4", "Jane Smith", R.drawable.image),
            Friend("5", "John Doe", R.drawable.image),
        )
    )

    val friends: List<Friend> = _friends.value
}