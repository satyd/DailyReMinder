package com.levp.dailyreminder.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReminderEntity(
    val title: String,
    val description: String?,
    val time: String = "",
    val eventDate: String,
    @PrimaryKey val id: Int? = null
)
