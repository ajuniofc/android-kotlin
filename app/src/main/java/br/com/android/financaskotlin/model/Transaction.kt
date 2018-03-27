package br.com.android.financaskotlin.model

import java.math.BigDecimal
import java.util.Calendar

/**
 * Created by admin on 27/03/2018.
 */
class Transaction(val value: BigDecimal,
                  val category: String,
                  val date: Calendar)