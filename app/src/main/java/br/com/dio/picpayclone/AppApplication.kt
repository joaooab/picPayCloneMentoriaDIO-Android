package br.com.dio.picpayclone

import android.app.Application
import br.com.dio.picpayclone.di.serviceModule
import br.com.dio.picpayclone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule, serviceModule)
        }
    }
}