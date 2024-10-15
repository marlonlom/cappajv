/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.database.entities

import androidx.room.ColumnInfo

/**
 * Catalog item tuple for listings.
 *
 * @author marlonlom
 *
 * @property id Product item id.
 * @property title Product item title.
 * @property picture Product item picture url.
 * @property category Product item category.
 * @property samplePunctuation Sample punctuation text.
 * @property punctuationsCount Punctuations count.
 */
data class CatalogItemTuple(
  @ColumnInfo
  val id: Long,
  @ColumnInfo
  val title: String,
  @ColumnInfo
  val picture: String,
  @ColumnInfo
  val category: String,
  @ColumnInfo
  val samplePunctuation: String,
  @ColumnInfo
  val punctuationsCount: Int,
)
