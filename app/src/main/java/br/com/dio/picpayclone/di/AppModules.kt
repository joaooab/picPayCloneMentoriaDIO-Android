package br.com.dio.picpayclone.di

import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.repository.TransacaoRepository
import br.com.dio.picpayclone.repository.TransacaoRepositoryImpl
import br.com.dio.picpayclone.services.ApiService
import br.com.dio.picpayclone.services.RetrofitService
import br.com.dio.picpayclone.ui.ajuste.AjusteViewModel
import br.com.dio.picpayclone.ui.home.HomeViewModel
import br.com.dio.picpayclone.ui.login.LoginViewModel
import br.com.dio.picpayclone.ui.pagar.PagarViewModel
import br.com.dio.picpayclone.ui.transacao.TransacaoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ComponentesViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { PagarViewModel(get()) }
    viewModel { AjusteViewModel() }
    viewModel { TransacaoViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.create<ApiService>() }
}

val repositoryModule = module {
    single<TransacaoRepository> { TransacaoRepositoryImpl(get(), get()) }
}

val daoModule = module {
    single { AppDatabase.getInstance(androidContext()).transacaoDAO() }
}