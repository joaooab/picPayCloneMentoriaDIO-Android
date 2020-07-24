package br.com.dio.picpayclone.data

data class Transferencia(
    val codigo: String = "",
    val origem: Usuario = Usuario(),
    val destino: Usuario = Usuario(),
    val dataHora: String = "",
    val dataisCartaoCredito: Boolean = false,
    val valor: Double = 0.0,
    val cartaoCredito: CartaoCredito = CartaoCredito()
)
