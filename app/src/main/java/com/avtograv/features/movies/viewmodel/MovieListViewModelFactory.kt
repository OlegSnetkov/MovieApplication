package com.avtograv.features.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avtograv.domain.MovieRepository

@Suppress("UNCHECKED_CAST")
internal class MovieListViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MoviesListViewModelImpl(repository) as T
}