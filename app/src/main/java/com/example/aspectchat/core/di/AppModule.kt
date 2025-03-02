package com.example.aspectchat.core.di

import com.example.aspectchat.core.data.network.MainServerApiService
import com.example.aspectchat.core.helpers.Env
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun retrofitFastifyServer(): MainServerApiService = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(Env.mainServerUrl)
        .build()
        .create(MainServerApiService::class.java)
}