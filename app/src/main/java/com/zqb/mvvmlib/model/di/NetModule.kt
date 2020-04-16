package com.zqb.mvvmlib.model.di

import com.zqb.mvvmlib.BuildConfig
import com.zqb.mvvmlib.model.Api
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val NET_MODULE_TAG = "NET_MODULE_TAG"

const val TIME_OUT_SECONDS = 10

var BASE_URL =  when (BuildConfig.DEBUG) {
    true -> "http://112.94.22.29:5003"
    false -> "http://112.94.22.29:5003"
}

val netKodeinModule = Kodein.Module(NET_MODULE_TAG) {

    bind<Api>() with singleton {
        instance<Retrofit>().create(Api::class.java)
    }

    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder().apply {
            connectTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
            readTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
        }.build()
    }

    bind<Retrofit>() with singleton {
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            client(instance())
        }.build()
    }
}