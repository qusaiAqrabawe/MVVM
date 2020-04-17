package org.qusai.qusaiandroid.mvvm.client

import okhttp3.OkHttpClient
import org.qusai.qusaiandroid.mvvm.data.Services
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL="https://jobs11.000webhostapp.com/jobs/";
    val okHttpClient=OkHttpClient.Builder().build()
    val instance: Services by lazy {
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(Services::class.java)

    }
}