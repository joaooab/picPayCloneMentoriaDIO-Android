package br.com.dio.picpayclone.data

import java.util.*

data class Transferencia(
    val codigo: String = "",
    val origem: Usuario = Usuario(),
    val destino: Usuario = Usuario(),
    val dataHora: String = "",
    val dataisCartaoCredito: Boolean = false,
    val valor: Double = 0.0,
    val cartaoCredito: CartaoCredito = CartaoCredito()
) {
    companion object {
        fun gerarHash(): String = UUID.randomUUID().toString()
    }
}
