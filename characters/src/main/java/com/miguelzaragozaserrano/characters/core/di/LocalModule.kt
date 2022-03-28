package com.miguelzaragozaserrano.characters.core.di

import android.content.Context
import androidx.room.Room
import com.miguelzaragozaserrano.characters.core.database.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {
    @Singleton
    @Provides
    fun provideCharactersDatabase(@ApplicationContext appContext: Context): CharactersDatabase =
        Room.databaseBuilder(
            appContext,
            CharactersDatabase::class.java,
            "characters"
        ).build()
}