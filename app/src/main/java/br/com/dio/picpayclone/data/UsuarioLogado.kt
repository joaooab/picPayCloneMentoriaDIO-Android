package br.com.dio.picpayclone.data

object UsuarioLogado {

    val usuario = Usuario(
        "joaovf",
        "123456",
        "joaofreitas@gmail.com",
        "João Vitor Freitas",
        "62992920466",
        "1980-06-20",
        "62992920466",
        0.0
    )

    fun setSaldo(saldo: Double) {
        usuario.saldo = saldo
    }

}