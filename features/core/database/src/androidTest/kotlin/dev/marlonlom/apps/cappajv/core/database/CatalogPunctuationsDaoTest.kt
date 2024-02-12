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
import dev.marlonlom.apps.cappajv.core.database.dao.CatalogPunctuationsDao
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogPunctuation
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CatalogPunctuationsDaoTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var database: CappaDatabase
  private lateinit var dao: CatalogPunctuationsDao

  @Before
  fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(context, CappaDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    dao = database.catalogPunctuationsDao()
  }

  @After
  fun teardown() {
    database.close()
  }

  @Test
  fun shouldInsertProductItemPoint() = runBlocking {
    val entity = CatalogPunctuation(11L, 1L, "Unidad", 1234L)
    dao.insertAll(entity)
    val list = dao.findByProduct(1L).first()
    assertThat(list).contains(entity)
  }

  @Test
  fun shouldInsertThenDeleteAllProductItemPoints() = runBlocking {
    val entity = CatalogPunctuation(11L, 1L, "Unidad", 1234L)
    dao.insertAll(entity)
    dao.deleteAll()
    val list = dao.findByProduct(1L).first()
    assertThat(list).isEmpty()
  }
}
