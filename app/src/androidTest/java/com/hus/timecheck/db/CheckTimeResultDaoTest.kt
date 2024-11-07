package com.hus.timecheck.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.hus.timecheck.models.TimeCheckResult
import com.hus.timecheck.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CheckTimeResultDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: CheckTimeResultDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDB() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.checkTimeResultDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun saveAndReadCheckTimeResult_Test() = runTest(UnconfinedTestDispatcher()) {

        val result = TimeCheckResult(1, 2, 8, 19, false)
        val row = dao.addResult(result)
        assertThat(row).isGreaterThan(-1)

        val results = dao.readAllData().getOrAwaitValue()

        assertThat(results).isNotNull()
        assertThat(results).contains(result)
        assertThat(results).hasSize(1)

    }

}