package br.com.android.financaskotlin.extension

import java.util.Calendar

/**
 * Created by admin on 27/03/2018.
 */

fun Calendar.formatToBrazilian(): String{
    val format = java.text.SimpleDateFormat("dd/MM/yyy")
    return format.format(this.time)
}
