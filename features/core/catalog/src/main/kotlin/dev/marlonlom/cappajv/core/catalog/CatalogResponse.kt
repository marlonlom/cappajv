/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.core.catalog

import kotlinx.serialization.Serializable

/**
 * Catalog item data class.
 *
 * @author marlonlom
 *
 * @property id item id as number.
 * @property title item title as text.
 * @property picture item picture url.
 * @property category item category.
 * @property detail item detail text.
 * @property punctuations list of punctuations.
 */
@Serializable
data class CatalogItem(
  val id: Long,
  val title: String,
  val picture: String,
  val category: String,
  val detail: String,
  val punctuations: List<Punctuation>,
)

/**
 * Punctuation data class.
 *
 * @author marlonlom
 *
 * @property label punctuation label text.
 * @property pointsQty punctuation quantity value.
 */
@Serializable
data class Punctuation(
  val label: String,
  val pointsQty: Int,
)

/**
 * A generic class that holds a value or an exception
 *
 * @author marlonlom
 *
 * @param R
 */
sealed class Response<out R> {
  data class Success<out T>(val data: T) : Response<T>()
  data class Failure(val exception: Exception) : Response<Nothing>()
}

/**
 * inline function that returns the success state value or a default value.
 *
 * @author marlonlom
 *
 * @param T
 * @param fallback default result value
 * @return success state value or a fallback value
 */
fun <T> Response<T>.successOr(fallback: T): T {
  return (this as? Response.Success<T>)?.data ?: fallback
}

/**
 * inline function that returns the detailed exception for failure state
 *
 * @author marlonlom
 *
 * @param T
 * @return failure state value
 */
fun <T> Response<T>.failure(): Exception = (this as? Response.Failure)?.exception ?: Exception()
