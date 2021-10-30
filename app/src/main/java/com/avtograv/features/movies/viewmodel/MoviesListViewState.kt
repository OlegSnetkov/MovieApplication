package com.avtograv.features.movies.viewmodel

import com.avtograv.model.Movie

internal sealed class MoviesListViewState {
    data class MoviesLoaded(val movies: List<Movie>) : MoviesListViewState()
    data class FailedToLoad(val exception: Throwable) : MoviesListViewState()
}