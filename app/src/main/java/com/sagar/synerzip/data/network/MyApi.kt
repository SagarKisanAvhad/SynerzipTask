package com.sagar.synerzip.data.network

import com.sagar.synerzip.data.network.responses.QuoteResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {

    @GET("quotes")
    suspend fun quotes(): Response<QuoteResponse>


    companion object {
        operator fun invoke(networkInterceptor: NetworkConnectionInterceptor): MyApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("api.openweathermap.org/data/2.5/weather?q=pune&APPID=0b886b91b986aac28db1bbbf27fda90b")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}