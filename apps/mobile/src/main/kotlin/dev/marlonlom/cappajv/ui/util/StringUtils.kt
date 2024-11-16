/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.util

import java.text.Normalizer
import java.util.Locale

inline val String.toSentenceCase: String
  get() = this.lowercase(Locale.getDefault()).replaceFirstChar { it.uppercaseChar() }

inline val String.slug: String
  get() = Normalizer.normalize(this, Normalizer.Form.NFD)
    .replace("\\p{Mn}+".toRegex(), "")
    .lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")
