/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.catalog_source

import kotlinx.serialization.json.Json
import java.io.InputStream

/**
 * Catalog data service class.
 *
 * @author marlonlom
 *
 */
class CatalogDataService {

  private var catalogJsonPath = CATALOG_JSON_FILENAME
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
    private const val CATALOG_JSON_FILENAME = "catalog.json"
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
