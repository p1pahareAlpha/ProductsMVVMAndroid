package com.example.myapplication.data.products.repository

import com.example.myapplication.data.products.entity.Product
import com.example.myapplication.data.products.entity.ProductData
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsAPI {
    @GET("/products")
    suspend fun getProductList() : ProductData
    @GET("/products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int ) : Product
}