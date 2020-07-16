package br.com.dio.picpayclone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _saldo = MutableLiveData(0.0)
    val saldo: LiveData<Double> = _saldo

}