package br.com.dio.picpayclone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComponentesViewModel : ViewModel() {

    private val _componentes = MutableLiveData<Componentes>().also {
        it.value = temComponentes
    }
    val componentes: LiveData<Componentes> = _componentes

    var temComponentes = Componentes()
        set(value) {
            field = value
            _componentes.value = value
        }

}

data class Componentes(
    val bottomNavigation: Boolean = false
)