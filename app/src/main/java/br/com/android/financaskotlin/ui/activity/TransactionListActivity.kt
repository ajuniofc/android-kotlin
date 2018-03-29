package br.com.android.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.enum.Type
import br.com.android.financaskotlin.extension.formatToBrazilian
import br.com.android.financaskotlin.model.Transaction
import br.com.android.financaskotlin.ui.ResumeView
import br.com.android.financaskotlin.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.activity_transaction_list.*
import kotlinx.android.synthetic.main.card_resume.*
import java.math.BigDecimal

class TransactionListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_list)

        val transactions: List<Transaction> = exampleList()

        setResume(transactions)
        setAdapter(transactions)
    }

    private fun setResume(transactions: List<Transaction>) {
        val view = window.decorView
        val resumeView = ResumeView(this, view, transactions)
        resumeView.update()
    }
    private fun setAdapter(transactions: List<Transaction>) {
        lista_transacoes_listview.adapter = TransactionAdapter(transactions, this)
    }

    private fun exampleList() = listOf(Transaction(type = Type.EXPENSE, value = BigDecimal(20.50)),
            Transaction(BigDecimal(100.00), "Economia", Type.INCOME),
            Transaction(BigDecimal(35.98), "Churras com os parças", Type.EXPENSE))

}
