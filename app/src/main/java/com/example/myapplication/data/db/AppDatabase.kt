package com.example.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.ProductsDao
import com.example.myapplication.domain.products.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}