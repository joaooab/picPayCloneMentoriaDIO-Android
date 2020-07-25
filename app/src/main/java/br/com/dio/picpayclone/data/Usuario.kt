package br.com.dio.picpayclone.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Usuario(
    val login: String = "",
    val senha: String = "",
    val email: String = "",
    val nomeCompleto: String = "",
    val cpf: String = "",
    val dataNascimento: String = "",
    val numeroTelefone: String = "",
    var saldo: Double = 0.0
) : Parcelable
