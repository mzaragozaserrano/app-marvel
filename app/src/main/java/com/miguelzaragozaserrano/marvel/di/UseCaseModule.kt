package com.miguelzaragozaserrano.marvel.di

import com.miguelzaragozaserrano.characters.data.repositories.CharactersRepositoryImpl
import com.miguelzaragozaserrano.characters.domain.usecases.CharactersUseCase
import com.miguelzaragozaserrano.characters.domain.usecases.CharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bind(useCase: CharactersUseCaseImpl): CharactersUseCase

    companion object {
        @Provides
        fun providesUseCase(repositoryImpl: CharactersRepositoryImpl): CharactersUseCaseImpl {
            return CharactersUseCaseImpl(repositoryImpl)
        }
    }
}