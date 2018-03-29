package br.com.android.financaskotlin.extension

import java.util.Calendar


fun Calendar.formatToBrazilian(): String{
    val format = java.text.SimpleDateFormat("dd/MM/yyy")
    return format.format(this.time)
}
