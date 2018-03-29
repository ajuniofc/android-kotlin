package br.com.android.financaskotlin.model

import br.com.android.financaskotlin.enum.Type
import java.math.BigDecimal

class Resume(private val transactions: List<Transaction>) {
    // Single expression function
    fun income()= totalBy(Type.INCOME)

    // Single expression function como property
    // ou seja, o get vai retornar o metodo
    val expense get() = totalBy(Type.EXPENSE)

    fun total() = income().subtract(expense)

    private fun totalBy(type: Type): BigDecimal {
        // Quando lambida so tem uma funcao como parametro, os () sao opcionais
        val total = transactions
                .filter({ it.type == type })
                .sumByDouble{ it.value.toDouble() }
        return BigDecimal(total)
    }
}