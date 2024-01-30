/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database

import dev.marlonlom.apps.cappajv.core.database.dao.CatalogProductsDao
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogPunctuationsDao
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItem
import dev.marlonlom.apps.cappajv.core.database.entities.ProductItemPoint
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
  fun getAllProducts(): Flow<List<ProductItem>>

  /**
   * Return a single product with provided id as Flow.
   *
   * @param productId Product id.
   * @return Single product, or null.
   */
  fun findProduct(productId: Long): Flow<ProductItem?>

  /**
   * Return punctuations list for a single product using its id.
   *
   * @param productId Product id.
   * @return Punctuations list, or empty list.
   */
  fun getPunctuations(productId: Long): Flow<List<ProductItemPoint>>

  /**
   * Insert all product items.
   *
   * @param products Product items as typed array.
   */
  fun insertAllProducts(vararg products: ProductItem)

  /**
   * Insert all punctuations.
   *
   * @param punctuations Punctuation items as typed array.
   */
  fun insertAllPunctuations(vararg punctuations: ProductItemPoint)

  /**
   * Delete all products.
   */
  fun deleteAllProducts()

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
 * @property catalogProductsDao Catalog products dao.
 * @property catalogPunctuationsDao Catalog punctuations dao.
 */
class LocalDataSourceImpl(
  private val catalogProductsDao: CatalogProductsDao,
  private val catalogPunctuationsDao: CatalogPunctuationsDao,
) : LocalDataSource {

  override fun getAllProducts() = catalogProductsDao.getProducts()

  override fun findProduct(productId: Long) = catalogProductsDao.findProduct(productId)

  override fun getPunctuations(productId: Long) = catalogPunctuationsDao.findByProduct(productId)

  override fun insertAllProducts(vararg products: ProductItem) =
    catalogProductsDao.insertAll(*products)

  override fun insertAllPunctuations(vararg punctuations: ProductItemPoint) =
    catalogPunctuationsDao.insertAll(*punctuations)

  override fun deleteAllProducts() = catalogProductsDao.deleteAll()

  override fun deleteAllPunctuations() = catalogPunctuationsDao.deleteAll()

}
