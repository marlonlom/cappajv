/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogProductsDao
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CatalogProductsDaoTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var database: CappaDatabase
  private lateinit var dao: CatalogProductsDao

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
  fun shouldInsertProductItem() = runBlocking {
    val entity = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    dao.insertAll(entity)
    val list = dao.getProducts().first()
    assertThat(list).contains(entity)
  }


  @Test
  fun shouldInsertThenDeleteAllProductItems() = runBlocking {
    val entity = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    dao.insertAll(entity)
    dao.deleteAll()
    val list = dao.getProducts().first()
    assertThat(list).isEmpty()
  }


  @Test
  fun shouldInsertThenQueryDetailedProductItem() = runBlocking {
    val product = CatalogItem(
      id = 1L,
      title = "Pod",
      slug = "pod",
      titleNormalized = "pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      detail = "Lorem ipsum"
    )
    dao.insertAll(product)

    val found = dao.findProduct(1L).first()

    assertThat(found).isNotNull()
    assertThat(found).isEqualTo(product)
  }

}
