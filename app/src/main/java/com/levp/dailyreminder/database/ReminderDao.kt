package com.levp.dailyreminder.database


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity)

    @Delete
    suspend fun deleteReminder(reminder: ReminderEntity)

    @Query("SELECT * FROM reminderEntity WHERE id = :id")
    suspend fun getReminderById(id: Int): ReminderEntity?

    @Query("SELECT * FROM reminderEntity")
    fun getReminders(): Flow<List<ReminderEntity>>
}