package com.hus.timecheck.repository

import androidx.lifecycle.LiveData
import com.hus.timecheck.db.CheckTimeResultDao
import com.hus.timecheck.models.TimeCheckResult
import javax.inject.Inject

class CheckTimeResultRepository @Inject constructor(private val dao: CheckTimeResultDao) {
    
    suspend fun insert(result: TimeCheckResult): Long {
        return dao.addResult(result)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAllTimeCheckResults()
    }

    fun getAllResults(): LiveData<List<TimeCheckResult>> {
        return dao.readAllData()
    }
}