package com.example.data.remote.di

import com.example.data.remote.apis.ColombiaPresidentsApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api-colombia.com/api/v1/"

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideColombiaPresidentApiService(retrofit: Retrofit): ColombiaPresidentsApi {
        return retrofit.create(ColombiaPresidentsApi::class.java)
    }

}
