package com.example.aspectchat.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.example.aspectchat.core.data.datastore.UserAccount
import com.example.aspectchat.core.data.datastore.UserAccountSerializer
import com.example.aspectchat.core.data.datastore.UserPreferences
import com.example.aspectchat.core.data.datastore.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

val Context.userPreferencesDataStore by dataStore(
    fileName = "user-preferences",
    serializer = UserPreferencesSerializer
)

val Context.userAccountDataStore by dataStore(
    fileName = "user-account",
    serializer = UserAccountSerializer
)


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMainServerApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://192.168.1.113:3200/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext applicationContext: Context
    ): DataStore<UserPreferences> {
        return applicationContext.userPreferencesDataStore
    }


    @Provides
    @Singleton
    fun provideUserAccountDataStore(
        @ApplicationContext applicationContext: Context
    ): DataStore<UserAccount> {
        return applicationContext.userAccountDataStore
    }
}
