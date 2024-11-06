package com.hus.timecheck

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hus.timecheck.utils.Event

class CheckTimeViewModel : ViewModel() {
    companion object {
        const val TIME_MAX_INT_LIMIT = 24
    }

    val startTime = MutableLiveData<String>()
    val endTime = MutableLiveData<String>()
    val checkTime = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    fun checkTimeAndSaveResult() {
        if (startTime.value.isNullOrEmpty()) {
            statusMessage.value = Event("start Time null")
        } else if (endTime.value.isNullOrEmpty()) {
            statusMessage.value = Event("end time null")
        } else if (checkTime.value.isNullOrEmpty()) {
            statusMessage.value = Event("check time null")
        } else {
            val startTime = startTime.value!!.toInt()
            val endTime = endTime.value!!.toInt()
            val checkTime = checkTime.value!!.toInt()

            if (checkTime in minOf(startTime, endTime) .. maxOf(
                    startTime, endTime
                ) && checkTime != endTime
            ) {
                statusMessage.value = Event("IN")
            } else {
                statusMessage.value = Event("OUTSIDE")
            }
        }
    }

}