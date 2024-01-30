package dev.marlonlom.apps.cappajv.features.catalog_list

import dev.marlonlom.apps.cappajv.core.database.entities.ProductItem

/**
 * Catalog ui state sealed class.
 *
 * @author marlonlom
 */
sealed class CatalogListState {
  /**
   * Catalog ui state as loading state object.
   */
  data object Loading : CatalogListState()

  /**
   * Catalog ui state as empty results object.
   */
  data object Empty : CatalogListState()

  /**
   * Catalog ui state as non empty list results data class.
   *
   * @property list product items non empty list
   */
  data class Listing(val list: Map<String, List<ProductItem>>) : CatalogListState()
}
