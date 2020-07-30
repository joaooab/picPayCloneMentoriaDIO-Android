package br.com.dio.picpayclone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.dio.picpayclone.data.UsuarioLogado
import br.com.dio.picpayclone.data.transacao.Transacao
import br.com.dio.picpayclone.data.transacao.TransacaoDataSource
import br.com.dio.picpayclone.services.ApiService
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: ApiService) : ViewModel() {

    private val _saldo = MutableLiveData(0.0)
    val saldo: LiveData<Double> = _saldo
    lateinit var transferenciasPaginado: LiveData<PagedList<Transacao>>
    val onLoading = MutableLiveData<Boolean>()
    val onErrorSaldo = MutableLiveData<String>()
    val onErrorTransferencia = MutableLiveData<String>()

    init {
        if (UsuarioLogado.isUsuarioLogado()) {
            val login = UsuarioLogado.usuario.login
            obterSaldo(login)
            obterTransacoes(login)
        }
    }

    private fun obterTransacoes(login: String) {
        val config = configurarPaginacao()
        transferenciasPaginado = iniciarPaginacao(config, login).build()
    }

    private fun configurarPaginacao(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(TransacaoDataSource.PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }

    fun getTransferencias() = transferenciasPaginado

    private fun iniciarPaginacao(
        config: PagedList.Config,
        login: String
    ): LivePagedListBuilder<Int, Transacao> {
        val dataSourceFactory = object : DataSource.Factory<Int, Transacao>() {
            override fun create(): DataSource<Int, Transacao> {
                return TransacaoDataSource(
                    apiService,
                    viewModelScope,
                    login,
                    onLoading,
                    onErrorTransferencia
                )
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    private fun obterSaldo(login: String) {
        viewModelScope.launch {
            try {
                val novoSaldo = apiService.getSaldo(login).saldo
                UsuarioLogado.setSaldo(novoSaldo)
                _saldo.value = novoSaldo
            } catch (e: Exception) {
                onErrorSaldo.value = e.message
            }
        }
    }

}