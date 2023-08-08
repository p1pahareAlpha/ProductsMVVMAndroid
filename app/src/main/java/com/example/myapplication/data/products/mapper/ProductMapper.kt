package com.example.myapplication.data.products.mapper

import com.example.myapplication.data.products.entity.ProductData
import com.example.myapplication.data.products.entity.Product as remoteProduct
import com.example.myapplication.domain.products.model.*

fun ProductData.mapProductDataToProductModel(): ProductModel {
    return ProductModel(
        products = products.map {
            Product(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                discountPercentage = it.discountPercentage,
                rating = it.rating,
                stock = it.stock,
                brand = it.brand,
                category = it.category,
                thumbnail = it.thumbnail,
                images = it.images.joinToString("|")
            )
        },
        total = total,
        skip = skip,
        limit = limit,
    )
}
fun remoteProduct.mapDataToModel() : Product {
    return Product(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images.joinToString("|")
    )
}