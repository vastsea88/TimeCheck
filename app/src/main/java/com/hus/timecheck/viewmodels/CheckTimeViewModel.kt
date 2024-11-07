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
    val checkResults: LiveData<List<TimeCheckResult>>
        get() = repository.getAllResults()

    fun checkTimeAndSaveResult() {
        if (startTime.value.isNullOrEmpty()) {
            statusMessage.value = Event("開始時刻が空です。")
        } else if (endTime.value.isNullOrEmpty()) {
            statusMessage.value = Event("完了時刻が空です。")
        } else if (checkTime.value.isNullOrEmpty()) {
            statusMessage.value = Event("検索時刻が空です。")
        } else {
            val startTime = startTime.value!!.toInt()
            val endTime = endTime.value!!.toInt()
            val checkTime = checkTime.value!!.toInt()

            val result = checkTime in minOf(startTime, endTime)..maxOf(
                startTime, endTime
            ) && checkTime != endTime
            insert(TimeCheckResult(0, startTime, endTime, checkTime, result))
        }
    }

    private fun insert(result: TimeCheckResult) = viewModelScope.launch(Dispatchers.IO) {
        val newRowId = repository.insert(result)
        withContext(Dispatchers.Main) {
            if (newRowId <= -1) {
                statusMessage.value = Event("エラーが発生しました！")
            } else {
                startTime.value = ""
                endTime.value = ""
                checkTime.value = ""
            }
        }
    }


}