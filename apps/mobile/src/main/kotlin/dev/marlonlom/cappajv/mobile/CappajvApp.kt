/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dev.marlonlom.cappajv.mobile.di.mobileAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

/**
 * Cappajv mobile application class.
 *
 * @author marlonlom
 */
class CappajvApp :
  Application(),
  ImageLoaderFactory {

  override fun onCreate() {
    super.onCreate()
    setupTimber()
    initializeKoinConfig()
  }

  override fun newImageLoader(): ImageLoader = ImageLoader(this)
    .newBuilder()
    .memoryCachePolicy(CachePolicy.ENABLED)
    .memoryCache {
      MemoryCache.Builder(this)
        .maxSizePercent(0.1)
        .strongReferencesEnabled(true)
        .build()
    }
    .diskCachePolicy(CachePolicy.ENABLED)
    .diskCache {
      DiskCache.Builder()
        .maxSizePercent(0.03)
        .directory(cacheDir)
        .build()
    }
    .logger(DebugLogger())
    .build()

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  private fun initializeKoinConfig() {
    startKoin {
      androidContext(this@CappajvApp)
      androidLogger(Level.DEBUG)
      modules(mobileAppModule)
    }
  }
}
