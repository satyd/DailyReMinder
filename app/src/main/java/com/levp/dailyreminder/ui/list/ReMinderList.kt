package com.levp.dailyreminder.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.levp.dailyreminder.ReMinderViewModel
import com.levp.dailyreminder.util.UiEvent

@Composable
fun ReMinderList(
    onNavigate: (UiEvent.Navigate) -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: ReMinderViewModel,
) {
    val reminders = viewModel.reminders.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(ReminderListEvent.OnUndoDeleteClick)
                    }
                }

                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(reminders.value) { reminder ->
            ListItem(
                reminder = reminder,
                onEvent = viewModel::onEvent,
                onEdit = {
                    viewModel.onEvent(ReminderListEvent.OnEditReminderClick(reminder))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}