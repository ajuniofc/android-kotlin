package br.com.android.financaskotlin.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.enum.Type
import br.com.android.financaskotlin.extension.formatToBrazilian
import br.com.android.financaskotlin.extension.maxLength
import br.com.android.financaskotlin.model.Transaction
import kotlinx.android.synthetic.main.transaction_item.view.*


class TransactionAdapter(private val transactions: List<Transaction>,
                         private val context: Context) : BaseAdapter() {

    private val maxLength = 14

    override fun getView(poition: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)

        val transaction = transactions[poition]

        addIcon(transaction, view)
        addCategory(view, transaction)
        addDate(view, transaction)
        addValue(transaction, view)

        return view
    }

    private fun addDate(view: View, transaction: Transaction) {
        view.transacao_data.text = transaction.date.formatToBrazilian()
    }

    private fun addCategory(view: View, transaction: Transaction) {
        view.transacao_categoria.text = transaction.category.maxLength(maxLength)
    }

    private fun addValue(transaction: Transaction, view: View) {
        val color: Int = colorBy(transaction.type)
        view.transacao_valor.setTextColor(color)
        view.transacao_valor.text = transaction.value.formatToBrazilian()
    }

    private fun colorBy(type: Type): Int {
        if (type == Type.INCOME) {
            return ContextCompat.getColor(context, R.color.income)
        } else {
            return ContextCompat.getColor(context, R.color.expense)
        }
    }

    private fun addIcon(transaction: Transaction, view: View) {
        val icon = iconBy(transaction.type)
        view.transacao_icone.setBackgroundResource(icon)
    }

    private fun iconBy(type: Type): Int{
        return if (type == Type.INCOME) {
            R.drawable.ic_transaction_item_income
        } else {
            R.drawable.ic_transaction_item_expense
        }
    }

    override fun getItem(position: Int): Transaction {
        return transactions[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0;
    }

    override fun getCount(): Int {
        return transactions.size
    }
}