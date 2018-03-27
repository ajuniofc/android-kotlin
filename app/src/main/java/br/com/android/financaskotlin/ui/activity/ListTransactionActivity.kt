package br.com.android.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transactions = listOf("lkjkl", "mnbvc")

        lista_transacoes_listview.setAdapter(TransactionAdapter(transactions, this))
    }

}
