package com.levp.dailyreminder.database

import kotlinx.coroutines.flow.Flow

class ReminderRepositoryImpl(
    private val dao: ReminderDao
): ReminderRepository {

    override suspend fun insertReminder(reminder: ReminderEntity) {
        dao.insertReminder(reminder)
    }

    override suspend fun deleteReminder(reminder: ReminderEntity) {
        dao.deleteReminder(reminder)
    }

    override suspend fun getReminderById(id: Int): ReminderEntity? {
        return dao.getReminderById(id)
    }

    override fun getReminders(): Flow<List<ReminderEntity>> {
        return dao.getReminders()
    }
}