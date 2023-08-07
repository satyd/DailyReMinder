package com.levp.dailyreminder.classes

import java.time.LocalDate

sealed class Reminder(val date: LocalDate) {
    class Birthday(
        val name: String,
        val birthDate: LocalDate
    ) : Reminder(birthDate)

    class Event(
        val event: String,
        val description: String = "",
        val time: String = "",
        val eventDate: LocalDate
    ) : Reminder(eventDate)
}
