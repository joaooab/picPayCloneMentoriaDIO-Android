package br.com.dio.picpayclone.extension

import java.text.NumberFormat
import java.util.*

fun Double?.formatarMoeda(): String = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this) ?: "0.0"
