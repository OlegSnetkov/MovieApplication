package com.avtograv.features.moviedetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avtograv.common.model.Failure
import com.avtograv.common.model.Success
import com.avtograv.domain.MovieRepository
import com.avtograv.extensions.exhaustive
import com.avtograv.model.MovieDetails
import kotlinx.coroutines.launch
import com.avtograv.common.model.Result


internal class MovieDetailsViewModelImpl(private val repository: MovieRepository) : MovieDetailsViewModel() {

    override val stateOutput = MutableLiveData<MovieDetailsViewState>()

    fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.loadMovie(movieId)
            handleResult(movie)
        }
    }

    private fun handleResult(result: Result<MovieDetails?>) {
        when (result) {
            is Success -> handleMovieLoadResult(result.data)
            is Failure -> stateOutput.postValue(MovieDetailsViewState.FailedToLoad(result.exception))
        }.exhaustive
    }

    private fun handleMovieLoadResult(movie: MovieDetails?) {
        if (movie != null) {
            stateOutput.postValue(MovieDetailsViewState.MovieLoaded(movie))
        } else {
            stateOutput.postValue(MovieDetailsViewState.NoMovie)
        }
    }
}