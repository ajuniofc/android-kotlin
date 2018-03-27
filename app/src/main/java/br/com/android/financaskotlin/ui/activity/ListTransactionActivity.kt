package br.com.android.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.model.Transaction
import br.com.android.financaskotlin.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transactions = listOf(Transaction(BigDecimal(20.50),"Comida", Calendar.getInstance()),
                                  Transaction(BigDecimal(100.00), "Economia", Calendar.getInstance()))

        lista_transacoes_listview.setAdapter(TransactionAdapter(transactions, this))
    }

}
