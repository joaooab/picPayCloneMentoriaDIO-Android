package br.com.dio.picpayclone.services

import br.com.dio.picpayclone.data.UsuarioLogado
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val URL = "http://10.0.0.112:8080"

object RetrofitService {

    private fun criarHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val request = aplicarToken(chain)
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    private fun aplicarToken(chain: Interceptor.Chain): Request {
        return if (UsuarioLogado.isLogado()) {
            val token = UsuarioLogado.token
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "${token.tipo} ${token.token}")
                .build()
        } else {
            chain.request()
        }
    }

    val service: Retrofit = Retrofit.Builder()
        .client(criarHttpClient())
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> create() = service.create(T::class.java)

}