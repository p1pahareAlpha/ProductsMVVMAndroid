package com.example.myapplication.data.products.repository

import com.example.myapplication.data.local.ProductsDao
import com.example.myapplication.data.products.mapper.mapDataToModel
import com.example.myapplication.data.products.mapper.mapProductDataToProductModel
import com.example.myapplication.domain.products.model.Product
import com.example.myapplication.domain.products.model.ProductModel
import com.example.myapplication.domain.products.model.toProductModel
import com.example.myapplication.domain.products.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsAPI: ProductsAPI,
    private val productsDao: ProductsDao,
) : ProductsRepository {
    override suspend fun getProductList(): ProductModel {
        return if( productsDao.getProductCount() > 0) {
            productsDao.getAllProducts().toProductModel()
        }
        else {
            productsAPI.getProductList().mapProductDataToProductModel().run {
                productsDao.insertAllProductResults(this.products)
                this
            }
        }
    }

    override suspend fun getProductDetails(id: Int): Product {
        return if(productsDao.isProductExist(id)){
            productsDao.getProductDetails(id)
        }else {
            productsAPI.getProductDetails(id).mapDataToModel()
        }
    }
}