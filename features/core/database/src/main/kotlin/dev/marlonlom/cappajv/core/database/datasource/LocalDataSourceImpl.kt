/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.database.datasource

import dev.marlonlom.cappajv.core.database.dao.CatalogFavoriteItemsDao
import dev.marlonlom.cappajv.core.database.dao.CatalogItemsDao
import dev.marlonlom.cappajv.core.database.dao.CatalogPunctuationsDao
import dev.marlonlom.cappajv.core.database.dao.CatalogSearchDao
import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
import kotlinx.coroutines.flow.Flow

/**
 * Local data source concrete implementation class.
 *
 * @author marlonlom
 *
 * @property catalogItemsDao Catalog products dao.
 * @property catalogPunctuationsDao Catalog punctuations dao.
 * @property catalogFavoriteItemsDao Catalog favorite items dao.
 * @property catalogSearchDao Catalog search dao.
 */
class LocalDataSourceImpl(
  private val catalogItemsDao: CatalogItemsDao,
  private val catalogPunctuationsDao: CatalogPunctuationsDao,
  private val catalogFavoriteItemsDao: CatalogFavoriteItemsDao,
  private val catalogSearchDao: CatalogSearchDao,
) : LocalDataSource {

  override fun getAllProducts() = catalogItemsDao.getProducts()

  override fun findProduct(productId: Long) = catalogItemsDao.findProduct(productId)

  override fun getPunctuations(productId: Long) = catalogPunctuationsDao.findByProduct(productId)

  override suspend fun insertFavoriteProduct(favoriteItem: CatalogFavoriteItem) =
    catalogFavoriteItemsDao.insert(favoriteItem)

  override fun getFavorites(): Flow<List<CatalogFavoriteItem>> = catalogFavoriteItemsDao.getFavoriteItems()

  override fun searchProducts(searchText: String): Flow<List<CatalogItemTuple>> =
    catalogSearchDao.searchProducts(searchText)

  override fun insertAllProducts(vararg products: CatalogItem) = catalogItemsDao.insertAll(*products)

  override fun insertAllPunctuations(vararg punctuations: CatalogPunctuation) =
    catalogPunctuationsDao.insertAll(*punctuations)

  override fun deleteAllProducts() = catalogItemsDao.deleteAll()

  override fun deleteAllFavorites() = catalogFavoriteItemsDao.deleteAll()

  override suspend fun deleteFavorite(productId: Long) = catalogFavoriteItemsDao.delete(productId)

  override fun deleteAllPunctuations() = catalogPunctuationsDao.deleteAll()

  override fun isFavorite(productId: Long): Flow<Int> = catalogFavoriteItemsDao.isFavorite(productId)
}
