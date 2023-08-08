package com.levp.dailyreminder.ui.editreminder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levp.dailyreminder.database.ReminderEntity
import com.levp.dailyreminder.database.ReminderRepository
import com.levp.dailyreminder.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditReminderViewModel @Inject constructor(
    val repository: ReminderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var reminder by mutableStateOf<ReminderEntity?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val reminderId = savedStateHandle.get<Int>("reminderId") ?: -1
        if (reminderId != -1) {
            viewModelScope.launch {
                repository.getReminderById(reminderId)?.let { reminder ->
                    title = reminder.title
                    description = reminder.description ?: ""
                    this@EditReminderViewModel.reminder = reminder
                }
            }
        }
    }

    fun onEvent(event: EditReminderEvent) {
        when (event) {
            is EditReminderEvent.OnTitleChange -> {
                title = event.title
            }

            is EditReminderEvent.OnDescriptionChange -> {
                description = event.description
            }

            EditReminderEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "The title can't be empty"
                            )
                        )
                        return@launch
                    }
                    repository.insertReminder(
                        ReminderEntity(
                            title = title,
                            description = description,
                            time = "00:00",
                            eventDate = "2023-12-12",
                            id = reminder?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }

        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}