package com.hus.timecheck.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class TimeCheckRsult(
    var startTime: Int, var endTime: Int, var checkTime: Int, var checkResult: Boolean
) : Parcelable
