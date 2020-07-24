package br.com.dio.picpayclone.di

import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.services.ApiService
import br.com.dio.picpayclone.services.RetrofitService
import br.com.dio.picpayclone.ui.ajuste.AjusteViewModel
import br.com.dio.picpayclone.ui.home.HomeViewModel
import br.com.dio.picpayclone.ui.login.LoginViewModel
import br.com.dio.picpayclone.ui.pagar.PagarViewModel
import br.com.dio.picpayclone.ui.transferencia.TransferenciaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ComponentesViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { PagarViewModel(get()) }
    viewModel { AjusteViewModel() }
    viewModel { TransferenciaViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.create<ApiService>() }
}