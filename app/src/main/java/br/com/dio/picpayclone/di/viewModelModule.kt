package br.com.dio.picpayclone.di

import br.com.dio.picpayclone.ui.home.HomeViewModel
import br.com.dio.picpayclone.ui.login.LoginViewModel
import br.com.dio.picpayclone.ui.pagar.PagarViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { LoginViewModel() }
    viewModel { PagarViewModel() }
}