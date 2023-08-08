package com.levp.dailyreminder.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.levp.dailyreminder.database.ReminderEntity

@Composable
fun ListItem(
    reminder: ReminderEntity,
    onEvent: (ReminderListEvent) -> Unit,
    onEdit: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${reminder.eventDate}")
        Text(text = "${reminder.title}")
        /*when (reminder) {
            is Reminder.Birthday -> {
                Text(text = "День рождения у ${reminder.name}")
            }

            is Reminder.Event -> {
                Text(text = "${reminder.event} в ${reminder.time}")
            }
        }*/
        IconButton(onClick = onEdit, modifier = Modifier.size(36.dp)) {
            Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
        }
        IconButton(onClick = {
            onEvent(ReminderListEvent.OnDeleteReminderClick(reminder))
        }) {
            androidx.compose.material.Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}