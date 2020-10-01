package br.com.dio.picpayclone.di

import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.services.ApiService
import br.com.dio.picpayclone.services.RetrofitService
import br.com.dio.picpayclone.ui.ajuste.AjusteViewModel
import br.com.dio.picpayclone.ui.home.HomeViewModel
import br.com.dio.picpayclone.ui.pagar.PagarViewModel
import br.com.dio.picpayclone.ui.transacao.TransacaoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ComponentesViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { PagarViewModel(get()) }
    viewModel { AjusteViewModel() }
    viewModel { TransacaoViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.create<ApiService>() }
}