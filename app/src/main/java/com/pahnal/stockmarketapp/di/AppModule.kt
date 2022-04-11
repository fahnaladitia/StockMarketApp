package com.pahnal.stockmarketapp.di

import android.app.Application
import androidx.room.Room
import com.pahnal.stockmarketapp.data.local.room.StockDao
import com.pahnal.stockmarketapp.data.local.room.StockDatabase
import com.pahnal.stockmarketapp.data.remote.network.StockApi
import com.pahnal.stockmarketapp.domain.repository.IStockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi =
        Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase =
        Room
            .databaseBuilder(
                app.applicationContext,
                StockDatabase::class.java,
                StockDatabase.DATABASE_NAME
            ).build()


}