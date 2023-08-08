package com.example.myapplication.di.db

import android.content.Context
import androidx.room.Room
import com.example.myapplication.common.AppConstants
import com.example.myapplication.data.db.AppDatabase
import com.example.myapplication.data.local.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppConstants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideNameDao(appDatabase: AppDatabase) : ProductsDao {
        return appDatabase.productsDao()
    }
}