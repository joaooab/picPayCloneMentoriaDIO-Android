package br.com.dio.picpayclone.services

import br.com.dio.picpayclone.data.Transferencia
import br.com.dio.picpayclone.data.Usuario
import retrofit2.http.*

interface ApiService {

    @GET("/usuarios/contatos")
    suspend fun getTodosUsuarios(@Query("login") login: String): List<Usuario>

    @GET("/usuarios/{login}")
    suspend fun getUsuario(@Path("login") login: String): Usuario

    @GET("/usuarios/{login}/saldo")
    suspend fun getSaldo(@Path("login") login: String): Usuario

    @POST("/transacoes")
    suspend fun realizarTransferencia(@Body transferencia: Transferencia): Transferencia

    @GET("/transacoes")
    suspend fun getTransferencias(@Query("login") login: String): List<Transferencia>

}