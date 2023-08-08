package com.levp.dailyreminder.ui.list

import com.levp.dailyreminder.classes.Reminder
import com.levp.dailyreminder.database.ReminderEntity

sealed class ReminderListEvent {
    data class OnDeleteReminderClick(val reminder: ReminderEntity): ReminderListEvent()
    object OnUndoDeleteClick: ReminderListEvent()
    data class OnReminderClick(val reminder: ReminderEntity): ReminderListEvent()
    object OnAddReminderClick: ReminderListEvent()
}
