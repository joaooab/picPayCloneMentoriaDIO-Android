package br.com.dio.picpayclone.data.transacao

import br.com.dio.picpayclone.data.CartaoCredito
import br.com.dio.picpayclone.data.Usuario
import java.util.*

data class Transacao(
    val codigo: String = "",
    val origem: Usuario = Usuario(),
    val destino: Usuario = Usuario(),
    val dataHora: String = "",
    val isCartaoCredito: Boolean = false,
    val valor: Double = 0.0,
    val cartaoCredito: CartaoCredito = CartaoCredito()
) {
    companion object {
        fun gerarHash(): String = UUID.randomUUID().toString()
    }
}
