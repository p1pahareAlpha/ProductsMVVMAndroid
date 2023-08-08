package com.example.myapplication.domain.products.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductModel(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: String
)

fun List<Product>.toProductModel() : ProductModel {
    return ProductModel(products = this, total =  this.count() , limit = this.count(), skip = 0 )
}