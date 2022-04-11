package com.pahnal.stockmarketapp.di

import com.pahnal.stockmarketapp.data.csv.CSVParser
import com.pahnal.stockmarketapp.data.csv.CompanyListingsParser
import com.pahnal.stockmarketapp.data.repository.StockRepositoryImpl
import com.pahnal.stockmarketapp.domain.model.CompanyListing
import com.pahnal.stockmarketapp.domain.repository.IStockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(companyListingsParser: CompanyListingsParser): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(stockRepository: StockRepositoryImpl): IStockRepository
}