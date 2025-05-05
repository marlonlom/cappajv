/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.domain.catalog.detail

import dev.marlonlom.cappajv.core.database.entities.CatalogItem
import dev.marlonlom.cappajv.core.database.entities.CatalogPunctuation

/**
 * Data class definition for catalog item detail.
 *
 * @author marlonlom
 *
 * @property product Catalog product detail
 * @property isFavorite True/False if catalog product is marked as favorite.
 * @property points Catalog product points list
 */
data class CatalogDetailItem(val product: CatalogItem, val isFavorite: Boolean, val points: List<CatalogPunctuation>)
