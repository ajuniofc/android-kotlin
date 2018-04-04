package br.com.android.financaskotlin.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.R.string.value
import br.com.android.financaskotlin.enum.Type
import br.com.android.financaskotlin.extension.formatToBrazilian
import br.com.android.financaskotlin.model.Transaction
import br.com.android.financaskotlin.ui.ResumeView
import br.com.android.financaskotlin.ui.adapter.TransactionAdapter
import kotlinx.android.synthetic.main.activity_transaction_list.*
import kotlinx.android.synthetic.main.transaction_form.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class TransactionListActivity : AppCompatActivity() {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_list)

        uptdateTransactions()

        lista_transacoes_adiciona_receita
                .setOnClickListener{

                    val view = LayoutInflater.from(this).inflate(R.layout.transaction_form,
                            window.decorView as ViewGroup,
                            false)

                    val year = 2018
                    val month = 3
                    val day = 3

                    val today = Calendar.getInstance()
                    view.form_transacao_data.setText(today.formatToBrazilian())
                    view.form_transacao_data.setOnClickListener {
                        DatePickerDialog(this,
                                DatePickerDialog.OnDateSetListener { v, year, month, day ->
                                    val selectedDate = Calendar.getInstance()
                                    selectedDate.set(year, month, day)
                                    view.form_transacao_data.setText(selectedDate.formatToBrazilian())
                                }, year, month, day).show()
                    }

                    val adapter = ArrayAdapter.createFromResource(this,
                            R.array.income_categories, android.R.layout.simple_spinner_dropdown_item)
                    view.form_transacao_categoria.adapter = adapter

                    AlertDialog.Builder(this)
                            .setTitle(R.string.add_income)
                            .setView(view)
                            .setPositiveButton("Adicionar",
                                    { dialogInterface, i ->
                                        val textValue = view.form_transacao_valor.text.toString()
                                        val textDate = view.form_transacao_data.text.toString()
                                        val textCategory = view.form_transacao_categoria.selectedItem.toString()

                                        val value = try {
                                            BigDecimal(textValue)
                                        }catch (e: NumberFormatException){
                                            Toast.makeText(this, "Falha", Toast.LENGTH_SHORT).show()
                                            BigDecimal.ZERO
                                        }

                                        val brazilianFormat = SimpleDateFormat("dd/MM/yyyy")
                                        val convertDate = brazilianFormat.parse(textDate)
                                        val date = Calendar.getInstance()
                                        date.time = convertDate


                                        val transaction = Transaction(type = Type.INCOME, value = value,
                                                date = date, category = textCategory)

                                        transactions.add(transaction)
                                        uptdateTransactions()
                                        closeButton()
                                    })
                            .setNegativeButton("Cancelar", null)
                            .show()

                }

        lista_transacoes_adiciona_despesa
                .setOnClickListener{

                    val view = LayoutInflater.from(this).inflate(R.layout.transaction_form,
                            window.decorView as ViewGroup,
                            false)

                    AlertDialog.Builder(this)
                            .setTitle(R.string.add_expense).setView(view).show()

                }
    }

    private fun closeButton() {
        lista_transacoes_adiciona_menu.close(true)
    }

    private fun uptdateTransactions() {
        setResume()
        setAdapter()
    }

    private fun setResume() {
        val view = window.decorView
        val resumeView = ResumeView(this, view, transactions)
        resumeView.update()
    }
    private fun setAdapter() {
        lista_transacoes_listview.adapter = TransactionAdapter(transactions, this)
    }

    override fun onBackPressed() {
        if (lista_transacoes_adiciona_menu.isOpened){
            closeButton()
        }else{
            super.onBackPressed()
        }
    }
}
