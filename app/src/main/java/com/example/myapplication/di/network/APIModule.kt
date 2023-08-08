package com.example.myapplication.di.network

import com.example.myapplication.data.products.repository.ProductsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {
    @Singleton
    @Provides
    fun provideHomeAPI(retrofit: Retrofit): ProductsAPI {
        return retrofit.create(ProductsAPI::class.java)
    }
}