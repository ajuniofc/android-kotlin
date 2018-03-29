package br.com.android.financaskotlin.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formatToBrazilian(): String{
    val format = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return format.format(this).replace("R$", "R$ ")
}
