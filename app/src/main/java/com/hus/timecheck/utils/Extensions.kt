package com.hus.timecheck.utils

import android.text.InputFilter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.hus.timecheck.R
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun TextInputEditText.setMaxIntLimit(maxValue: Int) {
    filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
        try {
            val input = dest.toString().substring(0, dstart) + source.subSequence(
                start,
                end
            ) + dest.toString().substring(dend)

            val intValue = input.toInt()
            if (input.isEmpty() || intValue in 1..maxValue) {
                null
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.number_entered_outside_tips, maxValue),
                    Toast.LENGTH_SHORT
                ).show()
                ""
            }
        } catch (e: NumberFormatException) {
            ""
        }
    })
}

fun <T> LiveData<T>.getOrAwaitValue(): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    try {
        latch.await(2, TimeUnit.SECONDS)
    } finally {
        this.removeObserver(observer)
    }

    return data ?: throw KotlinNullPointerException("LiveData value was null")
}

