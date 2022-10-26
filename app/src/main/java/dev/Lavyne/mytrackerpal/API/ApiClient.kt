package dev.Lavyne.mytrackerpal.API

import com.chuckerteam.chucker.api.ChuckerInterceptor
import dev.Lavyne.mytrackerpal.MyTrackPal
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(ChuckerInterceptor(MyTrackPal.appContext))
        .build()

    var retrofit= Retrofit
        .Builder()
        .baseUrl("http://192.81.215.35")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun <T> buldApiClient(apiInterface:Class<T>):T{
        return retrofit.create(apiInterface)
    }

}
