package br.com.dio.picpayclone.ui.pagar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.picpayclone.data.Usuario
import br.com.dio.picpayclone.data.UsuarioLogado
import br.com.dio.picpayclone.services.ApiService
import kotlinx.coroutines.launch

class PagarViewModel(private val service: ApiService) : ViewModel() {

    private val _contatos = MutableLiveData<List<Usuario>>()
    val contatos: LiveData<List<Usuario>> = _contatos
    val onError = MutableLiveData<String>()

    init {
        UsuarioLogado.usuario?.let { usuario ->
            viewModelScope.launch {
                try {
                    val usuarios = service.getTodosUsuarios(usuario.login)
                    _contatos.value = usuarios
                } catch (e: Exception) {
                    onError.value = e.message
                }
            }
        }
    }

}