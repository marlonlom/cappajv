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
import com.google.common.truth.Truth
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogFavoriteItemsDao
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogFavoriteItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CatalogFavoriteItemsDaoTest {
  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var database: CappaDatabase
  private lateinit var dao: CatalogFavoriteItemsDao

  @Before
  fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(context, CappaDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    dao = database.catalogFavoriteItemsDao()
  }

  @After
  fun teardown() {
    database.close()
  }

  @Test
  fun shouldSeeEmptyFavoriteList() = runBlocking {
    val list = dao.getFavoriteItems().first()
    Truth.assertThat(list).isEmpty()
  }

  @Test
  fun shouldInsertCatalogFavoriteItem() = runBlocking {
    val entity = CatalogFavoriteItem(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dao.insertAll(entity)
    val list = dao.getFavoriteItems().first()
    Truth.assertThat(list).contains(entity)
  }

  @Test
  fun shouldInsertThenClearFavoriteItems() = runBlocking {
    val entity = CatalogFavoriteItem(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dao.insertAll(entity)
    dao.deleteAll()
    val list = dao.getFavoriteItems().first()
    Truth.assertThat(list).isEmpty()
  }

  @Test
  fun shouldInsertMultipleThenDeleteFavoriteItems() = runBlocking {
    val remainingItem = CatalogFavoriteItem(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    val entities = arrayOf(
      remainingItem,
      CatalogFavoriteItem(
        id = 2L,
        title = "Tinto",
        picture = "https://noimage.no.com/no.png",
        category = "CategoryOne",
        samplePunctuation = "",
        punctuationsCount = 0,
      )
    )
    dao.insertAll(*entities)
    dao.delete(2L)
    val list = dao.getFavoriteItems().first()
    Truth.assertThat(list).isNotEmpty()
    Truth.assertThat(list).contains(remainingItem)
  }

  @Test
  fun shouldInsertThenFailCheckThatIsFavoriteItem() = runBlocking {
    val actual = dao.isFavorite(1234L).first()
    Truth.assertThat(actual).isEqualTo(0)
  }

  @Test
  fun shouldInsertThenSuccessCheckThatIsFavoriteItem() = runBlocking {
    val entity = CatalogFavoriteItem(
      id = 1L,
      title = "Pod",
      picture = "https://noimage.no.com/no.png",
      category = "CategoryOne",
      samplePunctuation = "",
      punctuationsCount = 0,
    )
    dao.insertAll(entity)
    val actual = dao.isFavorite(entity.id).first()
    Truth.assertThat(actual).isEqualTo(1)
  }
}
