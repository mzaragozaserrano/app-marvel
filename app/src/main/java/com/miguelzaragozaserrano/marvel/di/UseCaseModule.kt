package com.miguelzaragozaserrano.marvel.di

import com.miguelzaragozaserrano.data.repositories.CharactersRepositoryImpl
import com.miguelzaragozaserrano.domain.usecases.GetCharacters
import com.miguelzaragozaserrano.domain.usecases.CharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindUseCase(useCase: CharactersUseCaseImpl): GetCharacters

    companion object {
        @Provides
        fun providesUseCase(repositoryImpl: CharactersRepositoryImpl): CharactersUseCaseImpl {
            return CharactersUseCaseImpl(repositoryImpl)
        }
    }
}