/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
@Entity(tableName = "cappa_product")
data class ProductItem(
  @PrimaryKey @ColumnInfo(name = "product_id")
  val id: Long,
  @ColumnInfo(name = "product_title")
  val title: String,
  @ColumnInfo(name = "product_slug")
  val slug: String,
  @ColumnInfo(name = "product_title_normalized")
  val titleNormalized: String,
  @ColumnInfo(name = "product_picture")
  val picture: String,
  @ColumnInfo(name = "product_category")
  val category: String,
  @ColumnInfo(name = "product_detail")
  val detail: String,
)

/**
 * Entity model data class for product item point aka punctuation.
 *
 * @author marlonlom
 *
 * @property id Punctuation id.
 * @property productId Punctuation product item id.
 * @property label Punctuation title.
 * @property points Punctuation points numeric value.
 */
@Entity(
  tableName = "cappa_punctuation",
  primaryKeys = ["punctuation_id", "punctuation_product_id"]
)
data class ProductItemPoint(
  @ColumnInfo(name = "punctuation_id")
  val id: Long,
  @ColumnInfo(name = "punctuation_product_id")
  val productId: Long,
  @ColumnInfo(name = "punctuation_label")
  val label: String,
  @ColumnInfo(name = "punctuation_points")
  val points: Long
)
