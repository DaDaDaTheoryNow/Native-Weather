package com.dadadadev.nativeweather.di

import com.dadadadev.nativeweather.data.location.DefaultLocationTracker
import com.dadadadev.nativeweather.data.repository.WeatherRepositoryImpl
import com.dadadadev.nativeweather.domain.location.LocationTracker
import com.dadadadev.nativeweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository
}