package br.com.dio.picpayclone.di

import br.com.dio.picpayclone.ui.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel() }
}