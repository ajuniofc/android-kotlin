package br.com.android.financaskotlin.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.maxLength(length: Int): String{
    if (this.length > length){
        val firstCaracter = 0
        return "${this.substring(firstCaracter, length)}..."
    }
    return this
}

fun String.convertToCalendar(): Calendar {
    val brazilianFormat = SimpleDateFormat("dd/MM/yyyy")
    val convertDate = brazilianFormat.parse(this)
    val date = Calendar.getInstance()
    date.time = convertDate
    return date
}