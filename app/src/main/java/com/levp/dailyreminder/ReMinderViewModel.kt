package com.levp.dailyreminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levp.dailyreminder.classes.Reminder
import com.levp.dailyreminder.database.ReminderEntity
import com.levp.dailyreminder.database.ReminderRepository
import com.levp.dailyreminder.ui.list.ReminderListEvent
import com.levp.dailyreminder.util.Routes
import com.levp.dailyreminder.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ReMinderViewModel @Inject constructor(
    val repository: ReminderRepository
) : ViewModel() {

    val reminders = repository.getReminders()

    val reminderList: MutableStateFlow<List<Reminder>> = MutableStateFlow(emptyList())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedReminder: ReminderEntity? = null

    fun loadDataFromDB() {
        viewModelScope.launch {
            reminderList.emit(
                listOf(
                    Reminder.Birthday("Someone", birthDate = LocalDate.parse("2014-12-12")),
                    Reminder.Birthday("Xdd", birthDate = LocalDate.now()),
                    Reminder.Event("Sdelats", eventDate = LocalDate.now()),
                )
            )
        }
    }

    fun onEvent(event: ReminderListEvent) {
        when (event) {
            is ReminderListEvent.OnAddReminderClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.EDIT_REMINDER))
            }

            is ReminderListEvent.OnDeleteReminderClick -> {
                viewModelScope.launch {
                    deletedReminder = event.reminder
                    repository.deleteReminder(event.reminder)
                    sendUiEvent(UiEvent.ShowSnackbar("Reminder Deleted", "Undo"))
                }
            }

            is ReminderListEvent.OnReminderClick -> {
                sendUiEvent(
                    UiEvent.Navigate(
                        Routes.EDIT_REMINDER + "?reminderId=${event.reminder.id}"
                    )
                )
            }

            is ReminderListEvent.OnUndoDeleteClick -> {
                deletedReminder?.let { reminder->
                    viewModelScope.launch {
                        repository.insertReminder(reminder)
                    }
                }
            }
        }
    }

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}