package com.miguelzaragozaserrano.marvel.di

import com.miguelzaragozaserrano.characters.data.datasource.CharactersDataSource
import com.miguelzaragozaserrano.characters.data.datasource.CharactersDataSourceImpl
import com.miguelzaragozaserrano.characters.data.repositories.CharactersRepositoryImpl
import com.miguelzaragozaserrano.characters.data.service.CharactersService
import com.miguelzaragozaserrano.characters.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindRemoteDataSource(dataSource: CharactersDataSourceImpl): CharactersDataSource

}