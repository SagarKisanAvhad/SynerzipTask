package com.sagar.synerzip.data.network

import com.sagar.synerzip.data.network.responses.WeatherResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("APPID") appId: String = API_KEY
    ): Response<WeatherResponse>


    companion object {
        operator fun invoke(networkInterceptor: NetworkConnectionInterceptor): MyApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}

const val API_KEY = "0b886b91b986aac28db1bbbf27fda90b"
const val BASE_URL = "https://api.openweathermap.org/data/2.5/"