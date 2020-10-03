package br.com.dio.picpayclone.data

import androidx.room.Entity

@Entity
data class TransacaoLocal(
    val codigo: String = "",
    val origem: String = "",
    val destino: String = "",
    val dataHora: String = "",
    val isCartaoCredito: Boolean = false,
    val valor: Double = 0.0
)