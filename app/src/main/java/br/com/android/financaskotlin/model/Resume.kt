package br.com.android.financaskotlin.model

import br.com.android.financaskotlin.enum.Type
import java.math.BigDecimal

class Resume(private val transactions: List<Transaction>) {
    fun income(): BigDecimal {
        return totalBy(Type.INCOME)
    }

    fun expense(): BigDecimal {
        return totalBy(Type.EXPENSE)
    }

    fun total(): BigDecimal {
        return income().subtract(expense())
    }

    private fun totalBy(type: Type): BigDecimal {
        var total = BigDecimal.ZERO
        for (transaction in transactions) {
            if (transaction.type == type) {
                total = total.plus(transaction.value)
            }
        }
        return total
    }
}