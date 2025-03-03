package com.example.aspectchat.navigation.main.navigationDrawer.presentation.view

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aspectchat.R
import com.example.aspectchat.navigation.main.model.UserData

@Composable
fun ColumnScope.NavigationItemsView() {
    val users = rememberSaveable {
        mutableStateOf(
            listOf(
                UserData("a", "Alice", "Online", R.drawable.image3),
                UserData("b", "Bob", "Away", R.drawable.image),
                UserData("c", "Charlie", "Offline", R.drawable.image2),
                UserData("d", "David", "Online", R.drawable.image3),
                UserData("e", "Eve", "Away", R.drawable.image),
                UserData("f", "Frank", "Offline", R.drawable.image2),
            )
        )
    }

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(
            items = users.value,
            key = { it.ID }
        ) { item ->
            NavigationItemView(
                navigationItem = item,
                selected = false,
                onClick = {}
            )
            Spacer(Modifier.height(10.dp))
        }
    }
}
