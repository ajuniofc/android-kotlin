package br.com.android.financaskotlin.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.delegate.TransactionDelegate
import br.com.android.financaskotlin.enum.Type
import br.com.android.financaskotlin.extension.convertToCalendar
import br.com.android.financaskotlin.extension.formatToBrazilian
import br.com.android.financaskotlin.model.Transaction
import kotlinx.android.synthetic.main.transaction_form.view.*
import java.math.BigDecimal
import java.util.*

class AddTransactionDialog(private val viewGroup: ViewGroup,
                           private val context: Context) {

    private val view = createLayout()
    private val editTextValue = view.form_transacao_valor
    private val editTextDate = view.form_transacao_data
    private val spinnerCategory = view.form_transacao_categoria

    fun createDialog(type: Type,transactionDelegate: TransactionDelegate) {
        configDateField()
        configCategoryField(type)
        showForm(type, transactionDelegate)
    }

    private fun showForm(type: Type, transactionDelegate: TransactionDelegate) {
        AlertDialog.Builder(context)
                .setTitle(titleBy(type))
                .setView(view)
                .setPositiveButton("Adicionar",
                        { _, _ ->
                            val textValue = editTextValue.text.toString()
                            val textDate = editTextDate.text.toString()
                            val textCategory = spinnerCategory.selectedItem.toString()

                            val value = convertValueField(textValue)
                            val date = textDate.convertToCalendar()


                            val transaction = Transaction(type = type, value = value,
                                    date = date, category = textCategory)

                            transactionDelegate.delegate(transaction)
                        })
                .setNegativeButton("Cancelar", null)
                .show()
    }

    private fun titleBy(type: Type): Int {
        if (type == Type.INCOME){
            return R.string.add_income
        }
        return R.string.add_expense
    }


    private fun convertValueField(textValue: String): BigDecimal {
        return try {
            BigDecimal(textValue)
        } catch (e: NumberFormatException) {
            BigDecimal.ZERO
        }
    }

    private fun configCategoryField(type: Type) {
        val adapter = ArrayAdapter.createFromResource(context,
                categoriesBy(type), android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter
    }

    private fun categoriesBy(type: Type): Int {
        if (type == Type.INCOME){
            return R.array.income_categories
        }
        return R.array.expense_categories
    }

    private fun configDateField() {
        val today = Calendar.getInstance()

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        editTextDate.setText(today.formatToBrazilian())
        editTextDate.setOnClickListener {
            DatePickerDialog(context,
                    { _, year, month, day ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, day)
                        editTextDate.setText(selectedDate.formatToBrazilian())
                    }, year, month, day).show()
        }
    }

    private fun createLayout(): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.transaction_form,
                viewGroup,
                false)
    }

}