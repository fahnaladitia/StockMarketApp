package com.pahnal.stockmarketapp.data.mapper

import com.pahnal.stockmarketapp.data.local.entity.CompanyListingEntity
import com.pahnal.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.pahnal.stockmarketapp.domain.model.CompanyInfo
import com.pahnal.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(name, symbol, exchange)
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(name, symbol, exchange)
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}