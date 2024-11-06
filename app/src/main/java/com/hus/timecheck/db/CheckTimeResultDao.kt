package com.hus.timecheck.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hus.timecheck.model.TimeCheckRsult

@Dao
interface CheckTimeResultDao {

    @Insert
    suspend fun addResult(result: TimeCheckRsult)

    @Query("DELETE FROM time_check_table")
    suspend fun deleteAllTimeCheckRsults()

    @Query("SELECT * FROM time_check_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<TimeCheckRsult>>

}