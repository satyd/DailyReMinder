package com.levp.dailyreminder.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.levp.dailyreminder.classes.Reminder

@Composable
fun ReMinderList(list: List<Reminder>) {
    LazyColumn() {
        itemsIndexed(list) { index, item ->
            ListItem(data = item) {

            }
        }
    }
}