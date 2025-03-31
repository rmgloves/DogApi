package com.rmgloves.dogapi.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dog.ceo/api"

    @Provides
    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ) = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
}
