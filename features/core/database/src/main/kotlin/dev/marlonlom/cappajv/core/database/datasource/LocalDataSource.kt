/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.database.datasource

import dev.marlonlom.cappajv.core.database.entities.CatalogFavoriteItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation
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
   * Return Catalog searched items by provided query text.
   *
   * @param searchText Query text.
   */
  fun searchProducts(searchText: String): Flow<List<CatalogItemTuple>>

  /**
   * Insert all product items.
   *
   * @param products Product items as typed array.
   */
  fun insertAllProducts(vararg products: CatalogItem)

  /**
   * Insert all favorite catalog products.
   *
   * @param favoriteItem Favorite catalog products as typed array.
   */
  suspend fun insertFavoriteProduct(favoriteItem: CatalogFavoriteItem)

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
  suspend fun deleteFavorite(productId: Long)

  /**
   * Delete all punctuations.
   */
  fun deleteAllPunctuations()

  /**
   * Returns 1 if a product with the provided ID exists as a favorite, otherwise returns 0.
   *
   * @param productId Product item id.
   * @return Number that indicates if product id exists as favorite, as Flow.
   */
  fun isFavorite(productId: Long): Flow<Int>

}

