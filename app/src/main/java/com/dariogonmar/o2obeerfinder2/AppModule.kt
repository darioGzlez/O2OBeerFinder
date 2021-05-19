package com.dariogonmar.o2obeerfinder2

import com.dariogonmar.o2obeerfinder2.services.Api
import com.dariogonmar.o2obeerfinder2.services.ApiIntermediary
import com.dariogonmar.o2obeerfinder2.services.ApiIntermediaryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun baseUrl() = "https://api.punkapi.com/v2/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiIntermediaryImpl): ApiIntermediary = apiHelper
}