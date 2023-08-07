package com.levp.dailyreminder.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.levp.dailyreminder.classes.Reminder

@Composable
fun ListItem(
    data: Reminder,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${data.date}")
        when (data) {
            is Reminder.Birthday -> {
                Text(text = "День рождения у ${data.name}")
            }

            is Reminder.Event -> {
                Text(text = "${data.event} в ${data.time}")
            }
        }
        IconButton(onClick = onClick, modifier = Modifier.size(36.dp)) {
            Icons.Filled.Notifications
        }
    }
}