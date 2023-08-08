package com.levp.dailyreminder.ui.editreminder

sealed class EditReminderEvent {
    data class OnTitleChange(val title: String): EditReminderEvent()
    data class OnDescriptionChange(val description: String): EditReminderEvent()
    object OnSaveTodoClick: EditReminderEvent()
}
