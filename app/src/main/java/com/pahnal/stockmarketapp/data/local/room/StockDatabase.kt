package com.pahnal.stockmarketapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pahnal.stockmarketapp.data.local.entity.CompanyListingEntity

@Database(
    entities = [CompanyListingEntity::class],
    version = 1,
)
abstract class StockDatabase : RoomDatabase() {
    abstract val dao: StockDao

    companion object {
        const val DATABASE_NAME = "stock.db"
    }
}