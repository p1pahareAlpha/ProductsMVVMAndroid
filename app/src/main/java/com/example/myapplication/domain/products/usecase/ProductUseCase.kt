package com.example.myapplication.domain.products.usecase

import com.example.myapplication.di.network.IoDispatcher
import com.example.myapplication.domain.products.model.Product
import com.example.myapplication.domain.products.model.ProductModel
import com.example.myapplication.domain.products.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getProducts(): ProductModel {
        return withContext(dispatcher) {
            productsRepository.getProductList()
        }
    }

    suspend fun getProductById(id: Int): Product {
        return withContext(dispatcher) {
            productsRepository.getProductDetails(id)
        }
    }
}