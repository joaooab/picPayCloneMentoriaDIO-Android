package br.com.dio.picpayclone.services

import br.com.dio.picpayclone.data.*
import retrofit2.http.*

interface ApiService {

    @POST("/autenticacao")
    suspend fun autenticar(@Body login: Login): Token

    @GET("/usuarios/contatos")
    suspend fun getTodosUsuarios(@Query("login") login: String): List<Usuario>

    @GET("/usuarios/{login}")
    suspend fun getUsuario(@Path("login") login: String): Usuario

    @GET("/usuarios/{login}/saldo")
    suspend fun getSaldo(@Path("login") login: String): Usuario

    @POST("/transacoes")
    suspend fun realizarTransacao(@Body transacao: Transacao): Transacao

    @GET("/transacoes")
    suspend fun getTransacoes(@Query("login") login: String): PageTransacao

}