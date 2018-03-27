package br.com.android.financaskotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.android.financaskotlin.R
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

        view.transacao_valor.text = transaction.value.toString()
        view.transacao_categoria.text = transaction.category
        view.transacao_data.text = transaction.date.formatToBrazilian()

        return view
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