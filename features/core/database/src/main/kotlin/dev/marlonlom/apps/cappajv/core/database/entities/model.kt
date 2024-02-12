/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Entity model data class for Catalog product item.
 *
 * @author marlonlom
 *
 * @property id Product item id.
 * @property title Product item title.
 * @property slug Product item slug.
 * @property titleNormalized Product item normalized title.
 * @property picture Product item picture url.
 * @property category Product item category.
 * @property detail Product item detail.
 */
@Entity(
  tableName = "catalog_item",
  primaryKeys = ["id", "category"]
)
data class CatalogItem(
  @ColumnInfo
  val id: Long,
  @ColumnInfo
  val title: String,
  @ColumnInfo
  val slug: String,
  @ColumnInfo
  val titleNormalized: String,
  @ColumnInfo
  val picture: String,
  @ColumnInfo
  val category: String,
  @ColumnInfo
  val detail: String,
)

/**
 * Entity model data class for Catalog product item.
 *
 * @author marlonlom
 *
 * @property id Product item id.
 * @property title Product item title.
 * @property picture Product item picture url.
 * @property category Product item category.
 */
@Entity(
  tableName = "catalog_item_favorite",
  primaryKeys = ["id", "category"]
)
data class CatalogFavoriteItem(
  @ColumnInfo
  val id: Long,
  @ColumnInfo
  val title: String,
  @ColumnInfo
  val picture: String,
  @ColumnInfo
  val category: String,
)

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

/**
 * Catalog item tuple for listings.
 *
 * @author marlonlom
 *
 * @property id Product item id.
 * @property title Product item title.
 * @property picture Product item picture url.
 * @property category Product item category.
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
)
