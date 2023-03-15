package com.app.torch.utils.extensions.date

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDateOnly(): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy")
    try {
        return formatter.format(this)
    } catch (e: Exception) {
    }

    return this.toString()
}

fun Date.formatDateOnly(dateOnly: String): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    var date = Calendar.getInstance().time
    try {
        date = formatter.parse(dateOnly)
    } catch (e: Exception) {
    }

    return date.formatDateOnly()
}