package com.levp.dailyreminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levp.dailyreminder.classes.Reminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class ReMinderViewModel : ViewModel() {

    val reminderList: MutableStateFlow<List<Reminder>> = MutableStateFlow(emptyList())

    fun loadDataFromDB(){
        viewModelScope.launch {
            reminderList.emit(
                listOf(
                    Reminder.Birthday("Someone", birthDate = LocalDate.parse("12-12-2014")),
                    Reminder.Birthday("Xdd", birthDate = LocalDate.now()),
                    Reminder.Event("Sdelats", eventDate = LocalDate.now()),
                )
            )
        }
    }


}