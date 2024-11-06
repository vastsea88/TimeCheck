package com.hus.timecheck.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hus.timecheck.models.TimeCheckResult
import com.hus.timecheck.repository.CheckTimeResultRepository
import com.hus.timecheck.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CheckTimeViewModel @Inject constructor(
    private val repository: CheckTimeResultRepository
) : ViewModel() {
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
            statusMessage.value = Event("Start Time null")
        } else if (endTime.value.isNullOrEmpty()) {
            statusMessage.value = Event("end time null")
        } else if (checkTime.value.isNullOrEmpty()) {
            statusMessage.value = Event("check time null")
        } else {
            val startTime = startTime.value!!.toInt()
            val endTime = endTime.value!!.toInt()
            val checkTime = checkTime.value!!.toInt()

            val result = checkTime in minOf(startTime, endTime)..maxOf(
                startTime, endTime
            ) && checkTime != endTime
            if (result) {
                statusMessage.value = Event("IN")
            } else {
                statusMessage.value = Event("OUTSIDE")
            }
            insert(TimeCheckResult(0, startTime, endTime, checkTime, result))
        }
    }

    private fun insert(result: TimeCheckResult) = viewModelScope.launch(Dispatchers.IO) {
        val newRowId = repository.insert(result)
        withContext(Dispatchers.Main) {
            if (newRowId > -1) {
                statusMessage.value = Event("Inserted Successfully! $newRowId")
            } else {
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }


}