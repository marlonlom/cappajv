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
import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CatalogItemsDaoTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var database: CappaDatabase
  private lateinit var dao: CatalogItemsDao

  @Before
  fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(context, CappaDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    dao = database.catalogProductsDao()
  }

  @After
  fun teardown() {
    database.close()
  }

  @Test
  fun shouldInsertCatalogItem() = runBlocking {
    val entity = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    val expectedTuple = CatalogItemTuple(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dao.insertAll(entity)
    val list = dao.getProducts().first()
    assertThat(list).contains(expectedTuple)
  }

  @Test
  fun shouldInsertThenDeleteAllCatalogItems() = runBlocking {
    val entity = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dao.insertAll(entity)
    dao.deleteAll()
    val list = dao.getProducts().first()
    assertThat(list).isEmpty()
  }

  @Test
  fun shouldInsertThenQueryDetailedCatalogItem() = runBlocking {
    val product = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dao.insertAll(product)

    val found = dao.findProduct(1L).first()

    assertThat(found).isNotNull()
    assertThat(found).isEqualTo(product)
  }
}
