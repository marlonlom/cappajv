/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Entity model data class for catalog item punctuation.
 *
 * @author marlonlom
 *
 * @property id Punctuation id.
 * @property catalogItemId Punctuation product item id.
 * @property label Punctuation title.
 * @property points Punctuation points numeric value.
 */
@Entity(
  tableName = "catalog_punctuation",
  primaryKeys = ["id", "catalogItemId"]
)
data class CatalogPunctuation(
  @ColumnInfo
  val id: Long,
  @ColumnInfo
  val catalogItemId: Long,
  @ColumnInfo
  val label: String,
  @ColumnInfo
  val points: Long
)
