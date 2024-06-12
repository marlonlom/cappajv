/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.catalog_source

import kotlinx.serialization.json.Json
import java.io.InputStream
import java.util.Locale

/**
 * Catalog data service class.
 *
 * @author marlonlom
 *
 * @property language Selected language for fetching catalog data, default to english (en).
 */
class CatalogDataService(
  private val language: String = Locale.ENGLISH.language
) {

  private var catalogJsonPath = if (language == "es") CATALOG_JSON_FILENAME else ENG_CATALOG_JSON_FILENAME

  internal fun changePath(jsonPath: String) {
    catalogJsonPath = jsonPath
  }

  /**
   * Fetches catalog data.
   *
   * @return Catalog items list.
   */
  fun fetchData(): Response<List<CatalogItem>> {
    return try {
      val readText = getJsonResourceAsStream()?.bufferedReader()?.readText()
        ?: return Response.Failure(
          CatalogDataNotFoundException()
        )
      Response.Success(Json.decodeFromString(readText))
    } catch (exception: RuntimeException) {
      Response.Failure(CatalogDataSerializationException(exception))
    }
  }

  private fun getJsonResourceAsStream(): InputStream? = this.javaClass.classLoader.getResourceAsStream(catalogJsonPath)

  companion object {
    private const val CATALOG_JSON_FILENAME = "es/catalog.json"
    private const val ENG_CATALOG_JSON_FILENAME = "en/catalog.json"
  }
}

/**
 * Catalog data not found exception class.
 *
 * @author marlonlom
 */
class CatalogDataNotFoundException : RuntimeException()

/**
 * Catalog data serialization failed exception class.
 *
 * @author marlonlom
 *
 * @constructor
 * Constructs the exception instance for catalog data serialization failure.
 *
 * @param cause Cause for the exception.
 */
class CatalogDataSerializationException(cause: Throwable) : RuntimeException(cause)
