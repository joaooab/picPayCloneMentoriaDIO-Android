package br.com.dio.picpayclone.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.picpayclone.data.Login
import br.com.dio.picpayclone.data.State
import br.com.dio.picpayclone.data.Usuario
import br.com.dio.picpayclone.data.UsuarioLogado
import br.com.dio.picpayclone.services.ApiService
import kotlinx.coroutines.launch

class LoginViewModel(private val apiService: ApiService) : ViewModel() {

    val usuarioState = MutableLiveData<State<Usuario>>()

    fun login(login: Login) {
        usuarioState.value = State.Loading()
        viewModelScope.launch {
            try {
                val token = apiService.autenticar(login)
                UsuarioLogado.token = token
                val usuario = apiService.getUsuario(login.usuario)
                UsuarioLogado.usuario = usuario
                usuarioState.value = State.Success(usuario)
            } catch (e: Exception) {
                usuarioState.value = State.Error(e)
            }
        }
    }

}