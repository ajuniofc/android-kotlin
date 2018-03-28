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
import br.com.android.financaskotlin.model.Transaction
import kotlinx.android.synthetic.main.transaction_item.view.*


/**
 * Created by JHUNIIN on 27/03/2018.
 */
class TransactionAdapter(transactions: List<Transaction>,
                         context: Context) : BaseAdapter() {

    private val transactions = transactions
    private val context = context

    override fun getView(poition: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)

        val transaction = transactions[poition]

        if(transaction.type == Type.INCOME){
            designLayout(view, R.color.income, R.drawable.ic_transaction_item_income);
        }else{
            designLayout(view, R.color.expense, R.drawable.ic_transaction_item_expense);
        }

        view.transacao_valor.text = transaction.value.toString()
        view.transacao_categoria.text = transaction.category
        view.transacao_data.text = transaction.date.formatToBrazilian()

        return view
    }

    private fun designLayout(view: View, color: Int, icon: Int) {
        view.transacao_valor.setTextColor(ContextCompat.getColor(context, color))
        view.transacao_icone.setBackgroundResource(icon)
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