package com.example.myapplication.di.network

import com.example.myapplication.data.products.repository.ProductsRepositoryImpl
import com.example.myapplication.domain.products.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository
}