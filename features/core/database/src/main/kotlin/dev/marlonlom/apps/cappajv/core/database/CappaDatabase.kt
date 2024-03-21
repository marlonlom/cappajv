/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogFavoriteItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogPunctuationsDao
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogSearchDao
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogPunctuation

/**
 * Cappajv room database class.
 *
 * @author marlonlom
 */
@Database(
  entities = [
    CatalogItem::class,
    CatalogFavoriteItem::class,
    CatalogPunctuation::class
  ],
  version = 6,
  exportSchema = false
)
abstract class CappaDatabase : RoomDatabase() {

  /**
   * Catalog products dao instance.
   *
   * @return Catalog dao
   */
  abstract fun catalogProductsDao(): CatalogItemsDao

  /**
   * Catalog favorite products dao instance.
   *
   * @return Catalog dao
   */
  abstract fun catalogFavoriteItemsDao(): CatalogFavoriteItemsDao

  /**
   * Catalog punctuations dao instance.
   *
   * @return Catalog dao
   */
  abstract fun catalogPunctuationsDao(): CatalogPunctuationsDao

  /**
   * Catalog search dao instance.
   *
   * @return Catalog dao
   */
  abstract fun catalogSearchDao(): CatalogSearchDao

  companion object {

    @Volatile
    private var instance: CappaDatabase? = null

    private const val DATABASE_NAME = "cappajv-db"

    /**
     * Returns an instance of Cappa database.
     *
     * @param context Application context.
     *
     * @return Database instance.
     */
    fun getInstance(context: Context): CappaDatabase {
      return instance ?: synchronized(this) {
        instance ?: buildDatabase(context).also { instance = it }
      }
    }

    private fun buildDatabase(
      context: Context
    ): CappaDatabase =
      Room.databaseBuilder(
        context = context,
        klass = CappaDatabase::class.java,
        name = DATABASE_NAME
      ).fallbackToDestructiveMigration()
        .build()
  }
}
