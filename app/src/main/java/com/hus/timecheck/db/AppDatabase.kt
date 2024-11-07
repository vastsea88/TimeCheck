package com.hus.timecheck.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hus.timecheck.models.TimeCheckResult

@Database(entities = [TimeCheckResult::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun checkTimeResultDao(): CheckTimeResultDao
}