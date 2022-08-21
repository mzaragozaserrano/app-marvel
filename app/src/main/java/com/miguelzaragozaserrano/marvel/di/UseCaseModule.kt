package com.miguelzaragozaserrano.marvel.di

import com.miguelzaragozaserrano.data.repositories.CharactersRepositoryImpl
import com.miguelzaragozaserrano.domain.usecases.CharactersUseCaseImpl
import com.miguelzaragozaserrano.domain.usecases.FavoriteCharactersUseCaseImpl
import com.miguelzaragozaserrano.domain.usecases.GetCharacters
import com.miguelzaragozaserrano.domain.usecases.GetFavoriteCharacters
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCharacters(charactersUseCaseImpl: CharactersUseCaseImpl): GetCharacters

    @Binds
    fun bindGetFavoriteCharacters(favoriteCharactersUseCaseImpl: FavoriteCharactersUseCaseImpl): GetFavoriteCharacters

    companion object {
        @Provides
        fun providesCharactersUseCaseImpl(repositoryImpl: CharactersRepositoryImpl): CharactersUseCaseImpl {
            return CharactersUseCaseImpl(repositoryImpl)
        }

        @Provides
        fun providesFavoriteCharactersUseCaseImpl(repositoryImpl: CharactersRepositoryImpl): FavoriteCharactersUseCaseImpl {
            return FavoriteCharactersUseCaseImpl(repositoryImpl)
        }
    }
}