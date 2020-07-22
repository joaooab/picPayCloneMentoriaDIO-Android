package br.com.dio.picpayclone.data

object UsuarioLogado {

    var usuario: Usuario? = null

    fun isUsuarioLogado() = usuario !== null

}