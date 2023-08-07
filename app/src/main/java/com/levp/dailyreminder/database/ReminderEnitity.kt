package com.levp.dailyreminder.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class ReminderEntity(
    val event: String,
    val description: String?,
    val time: String = "",
    val eventDate: LocalDate,
    @PrimaryKey val id: Int? = null
)
