package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.domain.products.model.Product

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProductResults(results: List<Product>)

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductDetails(id: Int): Product

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductCount(): Int

    @Query("SELECT EXISTS(SELECT * FROM products WHERE id = :id)")
    fun isProductExist(id : Int) : Boolean

}