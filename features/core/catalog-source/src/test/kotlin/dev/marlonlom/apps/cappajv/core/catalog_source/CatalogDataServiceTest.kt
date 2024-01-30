/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.catalog_source

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class CatalogDataServiceTest {

  private lateinit var catalogResponse: Response<List<CatalogItem>>

  @Before
  fun init() {
    catalogResponse = CatalogDataService().fetchData()
  }

  @Test
  fun shouldValidateCatalogDataIsNotEmpty() {
    val list = catalogResponse.successOr(emptyList())
    assertTrue(catalogResponse is Response.Success)
    assertTrue(list.isNotEmpty())
    assertEquals(39, list.size)
  }

  @Test
  fun shouldValidateExpectedCatalogItemExist() {
    val expectedItem = CatalogItem(
      id = 15396L,
      title = "Granizado",
      picture = "https://juanvaldez.com/wp-content/uploads/2022/10/Granizado-juan-Valdez.jpg",
      punctuations = listOf(
        Punctuation(
          label = "Mediano",
          pointsQty = 2225
        )
      ),
      category = "Bebidas frías",
      detail = "Es una bebida fría y refrescante de café con hielo granizado."
    )
    val list = catalogResponse.successOr(emptyList())
    val foundItem = list.single { it.id == 15396L }
    assertTrue(catalogResponse is Response.Success)
    assertNotNull(foundItem)
    assertEquals(expectedItem, foundItem)
  }

  @Test
  fun shouldValidateExpectedCatalogItemNotExist() {
    val expectedItem = CatalogItem(
      id = 19396L,
      title = "None",
      picture = "https://nopic.com/img/null.jpg",
      category = "",
      detail = "",
      punctuations = listOf()
    )
    val list = catalogResponse.successOr(emptyList())
    val foundItem = list.find { it.id == expectedItem.id }
    assertNotNull(list)
    assertNull(foundItem)
    assertNotEquals(expectedItem, foundItem)
  }

  @Test
  fun shouldValidateErrorFetchingWrongJsonData() {
    val service = CatalogDataService()
    service.changePath("none.json")
    catalogResponse = service.fetchData()
    assertTrue(catalogResponse is Response.Failure)
    assertNotNull(catalogResponse.failure())
    assertTrue(catalogResponse.failure() is CatalogDataNotFoundException)
  }

  @Test
  fun shouldValidateErrorWhileSerializingJsonData() {
    val service = CatalogDataService()
    service.changePath("catalog-single.json")
    catalogResponse = service.fetchData()
    assertTrue(catalogResponse is Response.Failure)
    assertNotNull(catalogResponse.failure())
    assertTrue(catalogResponse.failure() is CatalogDataSerializationException)
  }

}
