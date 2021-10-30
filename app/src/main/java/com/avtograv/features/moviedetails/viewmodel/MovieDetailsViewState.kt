package com.avtograv.features.moviedetails.viewmodel

import com.avtograv.model.MovieDetails


internal sealed class MovieDetailsViewState {

    data class MovieLoaded(val movie: MovieDetails) : MovieDetailsViewState()

    data class FailedToLoad(val exception: Throwable) : MovieDetailsViewState()

    object NoMovie : MovieDetailsViewState()
}