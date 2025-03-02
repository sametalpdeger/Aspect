package com.example.aspectchat.core.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MainServerApiService {
    @Headers("Content-Type: application/json")
    @POST("/")
    suspend fun run(@Body body: JSONObject): Any

    companion object {
        private var retrofitService: MainServerApiService? = null
        private var gson = GsonBuilder()
            .setLenient()
            .create()

        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private var client: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .build()

        fun getInstance(): MainServerApiService {
            if (retrofitService == null) {
                retrofitService = Retrofit.Builder()
                    .baseUrl("https://countries.trevorblades.com")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build().create(MainServerApiService::class.java)
            }
            return retrofitService!!
        }
    }
}