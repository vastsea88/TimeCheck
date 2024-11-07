package com.hus.timecheck.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "time_check_result_table")
data class TimeCheckResult(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "time_check_result_id")
    val id: Long,
    var startTime: Int,
    var endTime: Int,
    var checkTime: Int,
    var checkResult: Boolean
) : Parcelable
