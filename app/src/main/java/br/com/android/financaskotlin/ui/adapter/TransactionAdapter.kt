package br.com.android.financaskotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.android.financaskotlin.R
import br.com.android.financaskotlin.model.Transaction
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.text.SimpleDateFormat

/**
 * Created by JHUNIIN on 27/03/2018.
 */
class TransactionAdapter(transactions: List<Transaction>,
                         context: Context) : BaseAdapter() {

    private val transactions = transactions
    private val context = context

    override fun getView(poition: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transaction = transactions[poition]

        view.transacao_valor.text = transaction.value.toString()
        view.transacao_categoria.text = transaction.category

        val format = SimpleDateFormat("dd/MM/yyy")
        val formatedDate = format.format(transaction.date.time)
        view.transacao_data.text = formatedDate
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