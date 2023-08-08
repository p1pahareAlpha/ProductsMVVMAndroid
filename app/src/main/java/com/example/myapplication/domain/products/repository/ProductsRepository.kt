package com.example.myapplication.domain.products.repository

import com.example.myapplication.domain.products.model.Product
import com.example.myapplication.domain.products.model.ProductModel

interface ProductsRepository {
    suspend fun getProductList() : ProductModel
    suspend fun getProductDetails( id: Int) : Product

}