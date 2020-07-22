package br.com.dio.picpayclone.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Usuario(
    val login: String,
    val senha: String,
    val email: String = "",
    val nomeCompleto: String = "teste",
    val cpf: String = "",
    val dataNascimento: Calendar = Calendar.getInstance(),
    val numeroTelefone: String = "",
    val saldo: Double = 1000.0
) : Parcelable