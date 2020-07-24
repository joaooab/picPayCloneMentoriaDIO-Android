package br.com.dio.picpayclone.services

import br.com.dio.picpayclone.data.Transferencia
import br.com.dio.picpayclone.data.Usuario
import retrofit2.http.*

interface ApiService {

    @GET("/usuarios/{login}")
    suspend fun getUsuario(@Path("login") login: String): Usuario

    @GET("/usuarios/contatos")
    suspend fun getTodosUsuarios(@Query("login") login: String): List<Usuario>

    @POST("/transacoes")
    suspend fun realizarTransferencia(@Body transferencia: Transferencia): Transferencia

}