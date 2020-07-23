package br.com.dio.picpayclone.di

import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.ui.ajuste.AjusteViewModel
import br.com.dio.picpayclone.ui.home.HomeViewModel
import br.com.dio.picpayclone.ui.login.LoginViewModel
import br.com.dio.picpayclone.ui.pagar.PagarViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ComponentesViewModel() }
    viewModel { HomeViewModel() }
    viewModel { LoginViewModel() }
    viewModel { PagarViewModel() }
    viewModel { AjusteViewModel() }
}