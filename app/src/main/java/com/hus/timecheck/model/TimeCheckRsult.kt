package com.hus.timecheck.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "time_check_table")
data class TimeCheckRsult(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var startTime: Int,
    var endTime: Int,
    var checkTime: Int,
    var checkResult: Boolean
) : Parcelable
