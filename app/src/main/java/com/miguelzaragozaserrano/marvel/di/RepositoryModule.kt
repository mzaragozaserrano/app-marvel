package com.miguelzaragozaserrano.marvel.di

import com.miguelzaragozaserrano.data.datasource.CharactersDataSource
import com.miguelzaragozaserrano.data.datasource.CharactersDataSourceImpl
import com.miguelzaragozaserrano.data.repositories.CharactersRepository
import com.miguelzaragozaserrano.data.repositories.CharactersRepositoryImpl
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
    fun bindCharactersDataSource(dataSource: CharactersDataSourceImpl): CharactersDataSource

}