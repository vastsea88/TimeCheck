package com.hus.timecheck.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hus.timecheck.models.TimeCheckResult

@Dao
interface CheckTimeResultDao {

    @Insert
    suspend fun addResult(result: TimeCheckResult): Long

    @Query("DELETE FROM time_check_result_table")
    suspend fun deleteAllTimeCheckResults(): Int

    @Query("SELECT * FROM time_check_result_table ORDER BY time_check_result_id ASC")
    fun readAllData(): LiveData<List<TimeCheckResult>>

}