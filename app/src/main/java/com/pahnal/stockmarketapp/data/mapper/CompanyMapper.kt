package com.pahnal.stockmarketapp.data.mapper

import com.pahnal.stockmarketapp.data.local.entity.CompanyListingEntity
import com.pahnal.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        this.name,
        this.symbol,
        this.exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        this.name,
        this.symbol,
        this.exchange
    )
}