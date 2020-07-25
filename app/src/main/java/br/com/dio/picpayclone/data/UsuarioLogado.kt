package br.com.dio.picpayclone.data

object UsuarioLogado {

    lateinit var usuario: Usuario

    fun isUsuarioLogado() = this::usuario.isInitialized

    fun isUsuarioNaoLogado() = !isUsuarioLogado()

    fun setSaldo(saldo: Double) {
        usuario.saldo = saldo
    }

}