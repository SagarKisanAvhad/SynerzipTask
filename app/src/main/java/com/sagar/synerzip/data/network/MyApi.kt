package com.sagar.synerzip.data.network

import com.sagar.synerzip.data.network.responses.QuoteResponse
import com.sagar.synerzip.data.network.responses.WeatherResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("quotes")
    suspend fun quotes(): Response<QuoteResponse>


    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("APPID") appId: String = "0b886b91b986aac28db1bbbf27fda90b"
    ): Response<WeatherResponse>


    companion object {
        operator fun invoke(networkInterceptor: NetworkConnectionInterceptor): MyApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}