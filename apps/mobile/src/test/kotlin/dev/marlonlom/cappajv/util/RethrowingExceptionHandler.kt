/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.util

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RethrowingExceptionHandler : TestRule, Thread.UncaughtExceptionHandler {
  override fun uncaughtException(thread: Thread, throwable: Throwable): Nothing = throw UncaughtException(throwable)

  override fun apply(base: Statement, description: Description): Statement {
    return object : Statement() {
      @Throws(Throwable::class)
      override fun evaluate() {
      }
    }
  }
}

internal class UncaughtException(cause: Throwable) : Exception(cause)
