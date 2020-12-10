package br.com.dio.picpayclone.data

data class TransacaoNetwork(
    val codigo: String?,
    val origem: Usuario?,
    val destino: Usuario?,
    val dataHora: String?,
    val isCartaoCredito: Boolean?,
    val valor: Double?,
    val cartaoCredito: CartaoCredito?
)