package br.com.dio.picpayclone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.picpayclone.data.Transacao
import br.com.dio.picpayclone.data.UsuarioLogado
import br.com.dio.picpayclone.services.ApiService
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: ApiService) : ViewModel() {

    private val _saldo = MutableLiveData(0.0)
    val saldo: LiveData<Double> = _saldo
    private val _transferencias = MutableLiveData<List<Transacao>>()
    val transferencias: LiveData<List<Transacao>> = _transferencias
    val onLoading = MutableLiveData<Boolean>()
    val onErrorSaldo = MutableLiveData<String>()
    val onErrorTransferencia = MutableLiveData<String>()

    init {
        onLoading.value = true
        viewModelScope.launch {
            val login = UsuarioLogado.usuario.login
            obterSaldo(login)
            obterHistorico(login)
            onLoading.value = false
        }
    }

    private suspend fun obterHistorico(login: String) {
        try {
            val historico = apiService.getTransacoes(login)
            _transferencias.value = historico.content
        } catch (e: Exception) {
            onErrorTransferencia.value = e.message
        }
    }

    private suspend fun obterSaldo(login: String) {
        try {
            val novoSaldo = apiService.getSaldo(login).saldo
            UsuarioLogado.setSaldo(novoSaldo)
            _saldo.value = novoSaldo
        } catch (e: Exception) {
            onErrorSaldo.value = e.message
        }
    }

}