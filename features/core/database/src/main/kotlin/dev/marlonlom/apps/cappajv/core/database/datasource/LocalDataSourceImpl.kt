/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.datasource

import dev.marlonlom.apps.cappajv.core.database.dao.CatalogFavoriteItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogPunctuationsDao
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogPunctuation
import kotlinx.coroutines.flow.Flow

/**
 * Local data source concrete implementation class.
 *
 * @author marlonlom
 *
 * @property catalogItemsDao Catalog products dao.
 * @property catalogPunctuationsDao Catalog punctuations dao.
 * @property catalogFavoriteItemsDao Catalog favorite items dao.
 */
class LocalDataSourceImpl(
    private val catalogItemsDao: CatalogItemsDao,
    private val catalogPunctuationsDao: CatalogPunctuationsDao,
    private val catalogFavoriteItemsDao: CatalogFavoriteItemsDao,
) : LocalDataSource {

  override fun getAllProducts() = catalogItemsDao.getProducts()

  override fun findProduct(productId: Long) = catalogItemsDao.findProduct(productId)

  override fun getPunctuations(productId: Long) = catalogPunctuationsDao.findByProduct(productId)

  override fun insertAllFavoriteProducts(vararg favoriteItems: CatalogFavoriteItem) =
    catalogFavoriteItemsDao.insertAll(*favoriteItems)

  override fun getFavorites(): Flow<List<CatalogFavoriteItem>> = catalogFavoriteItemsDao.getFavoriteItems()

  override fun insertAllProducts(vararg products: CatalogItem) =
    catalogItemsDao.insertAll(*products)

  override fun insertAllPunctuations(vararg punctuations: CatalogPunctuation) =
    catalogPunctuationsDao.insertAll(*punctuations)

  override fun deleteAllProducts() = catalogItemsDao.deleteAll()

  override fun deleteAllFavorites() = catalogFavoriteItemsDao.deleteAll()

  override fun deleteFavorite(productId: Long) = catalogFavoriteItemsDao.delete(productId)

  override fun deleteAllPunctuations() = catalogPunctuationsDao.deleteAll()

}
