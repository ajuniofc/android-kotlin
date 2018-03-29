package br.com.android.financaskotlin.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.extension.formatToBrazilian
import br.com.android.financaskotlin.model.Resume
import br.com.android.financaskotlin.model.Transaction
import kotlinx.android.synthetic.main.card_resume.view.*
import java.math.BigDecimal

class ResumeView(private val context: Context,
                 private val view: View,
                 transactions: List<Transaction>) {

    private val resume = Resume(transactions)
    private val incomeColor = ContextCompat.getColor(context, R.color.income)
    private val expenseColor = ContextCompat.getColor(context, R.color.expense)

    fun addTotalIncome() {
        val total = resume.income()
        with(view.resumo_card_receita){
            setTextColor(incomeColor)
            text = total.formatToBrazilian()
        }
    }

    fun addTotalExpense() {
        val total = resume.expense()
        with(view.resumo_card_despesa){
            setTextColor(expenseColor)
            text = total.formatToBrazilian()
        }
    }

    fun addTotal(){
        val total = resume.total()
        val color = colorBy(total)
        with(view.resumo_card_total){
            setTextColor(color)
            text = total.formatToBrazilian()
        }
    }

    private fun colorBy(value: BigDecimal): Int {
        if (value >= BigDecimal.ZERO) {
            return incomeColor
        }
        return expenseColor
    }

}
