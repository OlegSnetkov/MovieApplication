package com.avtograv.di

import com.avtograv.domain.MovieRepository

internal interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}