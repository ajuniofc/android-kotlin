package br.com.android.financaskotlin.model

import br.com.android.financaskotlin.enum.Type
import java.math.BigDecimal
import java.util.Calendar

class Transaction(val value: BigDecimal,
                  val category: String = "Indefinida",
                  val type: Type,
                  val date: Calendar = Calendar.getInstance())