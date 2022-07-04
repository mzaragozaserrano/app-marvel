package com.miguelzaragozaserrano.marvel.di

import android.content.Context
import androidx.room.Room
import com.miguelzaragozaserrano.data.dao.CharactersDAO
import com.miguelzaragozaserrano.data.database.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideCharactersDao(database: CharactersDatabase): CharactersDAO {
        return database.charactersDao()
    }

    @Provides
    @Singleton
    fun provideCharactersLocal(@ApplicationContext appContext: Context): CharactersDatabase =
        Room.databaseBuilder(
            appContext,
            CharactersDatabase::class.java,
            "characters"
        ).build()

}