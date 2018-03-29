package br.com.android.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.enum.Type
import br.com.android.financaskotlin.model.Transaction
import br.com.android.financaskotlin.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.activity_transaction_list.*
import java.math.BigDecimal
import java.util.*

class TransactionListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_list)

        val transactions: List<Transaction> = exampleList()

        setAdapter(transactions)
    }

    private fun setAdapter(transactions: List<Transaction>) {
        lista_transacoes_listview.adapter = TransactionAdapter(transactions, this)
    }

    private fun exampleList() = listOf(Transaction(type = Type.EXPENSE, value = BigDecimal(20.50)),
            Transaction(BigDecimal(100.00), "Economia", Type.INCOME),
            Transaction(BigDecimal(88.00), "Churras com os parças", Type.EXPENSE))

}
