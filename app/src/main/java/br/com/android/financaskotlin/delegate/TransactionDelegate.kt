package br.com.android.financaskotlin.delegate

import br.com.android.financaskotlin.model.Transaction

interface TransactionDelegate {

    fun delegate(transaction: Transaction)
}