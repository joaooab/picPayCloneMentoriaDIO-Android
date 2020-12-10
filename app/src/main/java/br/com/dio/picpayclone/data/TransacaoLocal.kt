package br.com.dio.picpayclone.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transacao")
data class TransacaoLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val codigo: String = "",
    val origem: String = "",
    val destino: String = "",
    val dataHora: String = "",
    val isCartaoCredito: Boolean = false,
    val valor: Double = 0.0
)