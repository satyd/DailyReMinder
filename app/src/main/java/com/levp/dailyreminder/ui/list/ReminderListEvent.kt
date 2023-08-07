package com.levp.dailyreminder.ui.list

import com.levp.dailyreminder.classes.Reminder

sealed class ReminderListEvent {
    data class OnDeleteReminderClick(val reminder: Reminder): ReminderListEvent()
    data class OnDoneChange(val reminder: Reminder, val isDone: Boolean): ReminderListEvent()
    object OnUndoDeleteClick: ReminderListEvent()
    data class OnReminderClick(val reminder: Reminder): ReminderListEvent()
    object OnAddReminderClick: ReminderListEvent()
}
