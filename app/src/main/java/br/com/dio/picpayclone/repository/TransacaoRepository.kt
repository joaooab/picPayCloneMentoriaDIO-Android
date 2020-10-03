package br.com.dio.picpayclone.repository

import br.com.dio.picpayclone.data.Transacao
import br.com.dio.picpayclone.data.Usuario
import br.com.dio.picpayclone.data.toModel
import br.com.dio.picpayclone.services.ApiService

interface TransacaoRepository {

    suspend fun getSaldo(login: String): Usuario

    suspend fun getTransacoes(login: String): List<Transacao>
}

class TransacaoRepositoryImpl(private val apiService: ApiService) : TransacaoRepository {

    override suspend fun getSaldo(login: String): Usuario = apiService.getSaldo(login)

    override suspend fun getTransacoes(login: String) =
        apiService.getTransacoes(login).content.toModel()

}