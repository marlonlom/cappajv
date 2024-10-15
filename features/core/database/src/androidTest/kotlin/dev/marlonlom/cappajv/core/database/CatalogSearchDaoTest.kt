/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.cappajv.core.database.dao.CatalogItemsDao
import dev.marlonlom.cappajv.core.database.dao.CatalogSearchDao
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CatalogSearchDaoTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var database: CappaDatabase
  private lateinit var dao: CatalogSearchDao

  @Before
  fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(context, CappaDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    dao = database.catalogSearchDao()
    fillSampleCatalogItems(database.catalogProductsDao())
  }

  @After
  fun teardown() {
    clearSampleCatalogItems(database.catalogProductsDao())
    database.close()
  }

  @Test
  fun shouldSuccessAfterSearchProductByTitle() = runBlocking {
    val expectedTitle = "torta"
    val searchedProducts = dao.searchProducts("%$expectedTitle%").first()
    assertThat(searchedProducts).isNotEmpty()
    assertThat(searchedProducts).hasSize(2)
    assertThat(searchedProducts.filter { it.title.lowercase().contains(expectedTitle) }).hasSize(2)
  }

  @Test
  fun shouldFailAfterSearchProductByTitle() = runBlocking {
    val expectedTitle = "chanfle"
    val searchedProducts = dao.searchProducts("%$expectedTitle%").first()
    assertThat(searchedProducts).isEmpty()
    assertThat(searchedProducts.map { it.title.lowercase() }).doesNotContain(expectedTitle)
  }

  private fun clearSampleCatalogItems(catalogProductsDao: CatalogItemsDao) {
    catalogProductsDao.deleteAll()
  }

  private fun fillSampleCatalogItems(
    catalogProductsDao: CatalogItemsDao
  ) {
    listOf(
      "affogato", "almojabana", "cappuccino",
      "chai", "chocolate", "granizado",
      "pandebono", "torta de banano", "torta de zanahoria"
    ).mapIndexed { index, title ->
      CatalogItem(
        id = index.toLong() + 1,
        title = title,
        slug = title,
        titleNormalized = title,
        picture = "https://noimage.no.com/$title.png",
        category = "CategoryOne",
        detail = title,
        samplePunctuation = "",
        punctuationsCount = 0
      )
    }.also { items ->
      catalogProductsDao.insertAll(
        *items.toTypedArray()
      )
    }
  }
}
