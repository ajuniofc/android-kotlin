package br.com.android.financaskotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.android.financaskotlin.R

/**
 * Created by JHUNIIN on 27/03/2018.
 */
class TransactionAdapter(transactions: List<String>,
                         context: Context) : BaseAdapter() {

    private val transactions = transactions
    private val context = context

    override fun getView(poition: Int, view: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
    }

    override fun getItem(position: Int): String {
        return transactions[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0;
    }

    override fun getCount(): Int {
        return transactions.size
    }
}