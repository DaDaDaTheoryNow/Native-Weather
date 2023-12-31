package com.dadadadev.nativeweather.di

import com.dadadadev.nativeweather.features.weather.data.repository.LocationRepositoryImpl
import com.dadadadev.nativeweather.features.weather.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: LocationRepositoryImpl): LocationRepository
}