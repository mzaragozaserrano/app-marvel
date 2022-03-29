package com.miguelzaragozaserrano.marvel.di

import com.miguelzaragozaserrano.characters.datasource.CharactersDataSource
import com.miguelzaragozaserrano.characters.datasource.CharactersDataSourceImpl
import com.miguelzaragozaserrano.characters.repositories.CharactersRepositoryImpl
import com.miguelzaragozaserrano.characters.repositories.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindRemoteDataSource(dataSource: CharactersDataSourceImpl): CharactersDataSource

}