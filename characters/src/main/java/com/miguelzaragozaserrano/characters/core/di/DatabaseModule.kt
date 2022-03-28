package com.miguelzaragozaserrano.characters.core.di

import com.miguelzaragozaserrano.characters.core.dao.CharactersDAO
import com.miguelzaragozaserrano.characters.core.database.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCharactersDAO(database: CharactersDatabase): CharactersDAO = database.characterDao()

}