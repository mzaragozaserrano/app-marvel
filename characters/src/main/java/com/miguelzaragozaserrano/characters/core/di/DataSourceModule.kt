package com.miguelzaragozaserrano.characters.core.di

import com.miguelzaragozaserrano.characters.data.datasource.CharactersDataSourceImpl
import com.miguelzaragozaserrano.characters.data.local.CharactersLocal
import com.miguelzaragozaserrano.characters.data.service.CharactersService
import com.miguelzaragozaserrano.core.utils.NetworkHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideCharactersDataSource(
        networkHandler: NetworkHandler,
        service: CharactersService,
        local: CharactersLocal,
    ): CharactersDataSourceImpl = CharactersDataSourceImpl(networkHandler, service, local)
}