package br.com.android.financaskotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.delegate.TransactionDelegate
import br.com.android.financaskotlin.enum.Type
import br.com.android.financaskotlin.model.Transaction
import br.com.android.financaskotlin.ui.ResumeView
import br.com.android.financaskotlin.ui.adapter.TransactionAdapter
import br.com.android.financaskotlin.ui.dialog.AddTransactionDialog
import kotlinx.android.synthetic.main.activity_transaction_list.*

class TransactionListActivity : AppCompatActivity() {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_list)

        configResume()
        configAdapter()
        //Fab = Floating Action Button
        configFab()
    }

    private fun createDialogAddTransaction(type: Type) {
        AddTransactionDialog(window.decorView as ViewGroup, this)
                .createDialog(type, object : TransactionDelegate {
                    override fun delegate(transaction: Transaction) {
                        transactions.add(transaction)
                        uptdateTransactions()
                        closeButton()
                    }

                })
    }

    private fun closeButton() {
        lista_transacoes_adiciona_menu.close(true)
    }

    private fun uptdateTransactions() {
        configResume()
        configAdapter()
    }

    private fun configFab() {
        lista_transacoes_adiciona_receita
                .setOnClickListener {
                    createDialogAddTransaction(Type.INCOME)
                }

        lista_transacoes_adiciona_despesa
                .setOnClickListener {
                    createDialogAddTransaction(Type.EXPENSE)
                }
    }

    private fun configResume() {
        val view = window.decorView
        val resumeView = ResumeView(this, view, transactions)
        resumeView.update()
    }

    private fun configAdapter() {
        lista_transacoes_listview.adapter = TransactionAdapter(transactions, this)
    }

    override fun onBackPressed() {
        if (lista_transacoes_adiciona_menu.isOpened) {
            closeButton()
        } else {
            super.onBackPressed()
        }
    }
}
