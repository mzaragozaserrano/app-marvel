package com.miguelzaragozaserrano.characters.core.di

import com.miguelzaragozaserrano.characters.data.datasource.CharactersDataSource
import com.miguelzaragozaserrano.characters.data.repositories.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCharactersRepository(charactersDataSource: CharactersDataSource) =
        CharactersRepositoryImpl(charactersDataSource = charactersDataSource)
}