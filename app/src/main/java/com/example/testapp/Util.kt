package com.example.testapp

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: String) : String {
    val originalFormat = SimpleDateFormat("ddMMyyyy", Locale.US)
    val newFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)

    // Parsing the original string to a Date object
    val date = originalFormat.parse(date)
    return newFormat.format(date)

}