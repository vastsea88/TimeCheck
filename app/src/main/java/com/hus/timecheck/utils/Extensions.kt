package com.hus.timecheck.utils

import android.text.InputFilter
import android.text.Spanned
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.hus.timecheck.R

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
