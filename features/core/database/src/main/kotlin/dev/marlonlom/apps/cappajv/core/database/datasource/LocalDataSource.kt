/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database

import dev.marlonlom.apps.cappajv.core.database.dao.CatalogFavoriteItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogItemsDao
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogPunctuationsDao
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogPunctuation
import kotlinx.coroutines.flow.Flow

/**
 *  Local data source concrete implementation class.
 *
 *  @author marlonlom
 */
interface LocalDataSource {

  /**
   * Returns all items catalog list.
   *
   * @return Catalog list
   */
  fun getAllProducts(): Flow<List<CatalogItemTuple>>

  /**
   * Return a single product with provided id as Flow.
   *
   * @param productId Product id.
   * @return Single product, or null.
   */
  fun findProduct(productId: Long): Flow<CatalogItem?>

  /**
   * Return punctuations list for a single product using its id.
   *
   * @param productId Product id.
   * @return Punctuations list, or empty list.
   */
  fun getPunctuations(productId: Long): Flow<List<CatalogPunctuation>>

  /**
   * Return favorite catalog items list.
   *
   * @return Favorite catalog items list, or empty list.
   */
  fun getFavorites(): Flow<List<CatalogFavoriteItem>>

  /**
   * Insert all product items.
   *
   * @param products Product items as typed array.
   */
  fun insertAllProducts(vararg products: CatalogItem)

  /**
   * Insert all favorite catalog products.
   *
   * @param favoriteItems Favorite catalog products as typed array.
   */
  fun insertAllFavoriteProducts(vararg favoriteItems: CatalogFavoriteItem)

  /**
   * Insert all punctuations.
   *
   * @param punctuations Punctuation items as typed array.
   */
  fun insertAllPunctuations(vararg punctuations: CatalogPunctuation)

  /**
   * Delete all products.
   */
  fun deleteAllProducts()

  /**
   * Delete all favorite products.
   */
  fun deleteAllFavorites()

  /**
   * Delete all favorite products.
   *
   * @param productId Product item id.
   */
  fun deleteFavorite(productId: Long)

  /**
   * Delete all punctuations.
   */
  fun deleteAllPunctuations()


}

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
