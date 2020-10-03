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
    val onLoadingSaldo = MutableLiveData<Boolean>()
    val onErrorSaldo = MutableLiveData<String>()
    val onLoadingTransferencia = MutableLiveData<Boolean>()
    val onErrorTransferencia = MutableLiveData<String>()

    init {
        val login = UsuarioLogado.usuario.login
        obterSaldo(login)
        obterHistorico(login)
    }

    private fun obterHistorico(login: String) {
        onLoadingTransferencia.value = true
        viewModelScope.launch {
            try {
                val historico = apiService.getTransacoes(login)
                _transferencias.value = historico.content
            } catch (e: Exception) {
                onErrorTransferencia.value = e.message
            }
            onLoadingTransferencia.value = false
        }
    }

    private fun obterSaldo(login: String) {
        onLoadingSaldo.value = true
        viewModelScope.launch {
            try {
                val novoSaldo = apiService.getSaldo(login).saldo
                UsuarioLogado.setSaldo(novoSaldo)
                _saldo.value = novoSaldo
            } catch (e: Exception) {
                onErrorSaldo.value = e.message
            }
            onLoadingSaldo.value = false
        }
    }

}