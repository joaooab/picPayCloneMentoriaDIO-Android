package br.com.dio.picpayclone.data

import java.util.*

data class Transacao(
    val codigo: String,
    val origem: Usuario = Usuario("teste", "teste"),
    val destino: Usuario =  Usuario("teste", "teste"),
    val dataHora: Calendar = Calendar.getInstance(),
    val valor: Double,
    val cartaoCredito: CartaoCredito = CartaoCredito()
)
