package com.pahnal.stockmarketapp.data.repository

import com.pahnal.stockmarketapp.data.csv.CSVParser
import com.pahnal.stockmarketapp.data.local.room.StockDatabase
import com.pahnal.stockmarketapp.data.mapper.toCompanyListing
import com.pahnal.stockmarketapp.data.mapper.toCompanyListingEntity
import com.pahnal.stockmarketapp.data.remote.network.StockApi
import com.pahnal.stockmarketapp.domain.model.CompanyListing
import com.pahnal.stockmarketapp.domain.repository.IStockRepository
import com.pahnal.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val db: StockDatabase,
    private val api: StockApi,
    private val companyListingsParser: CSVParser<CompanyListing>,
) : IStockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>> = flow {
        emit(Resource.Loading())

        val localListings = dao.searchCompanyListing(query)
        val data = localListings.map { it.toCompanyListing() }
        emit(Resource.Success(data))

        val isDbEmpty = localListings.isEmpty() && query.isBlank()
        val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

        if (shouldJustLoadFromCache) {
            emit(Resource.Loading(false))
            return@flow
        }
        val remoteListings = try {
            val response = api.getListings()
            companyListingsParser.parse(response.byteStream())
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "Couldn't load data"))
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(e.message ?: "Couldn't reach server"))
            null
        }
        remoteListings?.let { listings ->
            dao.clearCompanyListing()
            dao.insertCompanyListings(listings.map { it.toCompanyListingEntity() })

            emit(Resource.Success(
                data = dao.searchCompanyListing("")
                    .map { it.toCompanyListing() }
            ))
            emit(Resource.Loading(false))
        }
    }
}