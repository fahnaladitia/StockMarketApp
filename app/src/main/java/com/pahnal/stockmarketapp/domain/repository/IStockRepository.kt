package com.pahnal.stockmarketapp.domain.repository

import com.pahnal.stockmarketapp.domain.model.CompanyListing
import com.pahnal.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface IStockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

}