package com.levp.dailyreminder.database

import kotlinx.coroutines.flow.Flow

interface ReminderRepository {

    suspend fun insertReminder(reminder: ReminderEntity)

    suspend fun deleteReminder(reminder: ReminderEntity)

    suspend fun getReminderById(id: Int): ReminderEntity?

    fun getReminders(): Flow<List<ReminderEntity>>
}