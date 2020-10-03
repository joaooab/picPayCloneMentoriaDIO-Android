package br.com.dio.picpayclone.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "http://192.168.0.27:8080"

object RetrofitService {

    val service: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> create() = service.create(T::class.java)

}