package com.levp.dailyreminder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.levp.dailyreminder.database.ReminderDao
import com.levp.dailyreminder.database.ReminderEntity

@Database(
    entities = [ReminderEntity::class],
    version = 1
)
abstract class ReminderDatabase: RoomDatabase() {

    abstract val dao: ReminderDao
}