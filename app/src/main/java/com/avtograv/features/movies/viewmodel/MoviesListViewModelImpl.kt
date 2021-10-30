package com.avtograv.features.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avtograv.common.model.Failure
import com.avtograv.common.model.Success
import com.avtograv.common.model.Result
import com.avtograv.domain.MovieRepository
import com.avtograv.extensions.exhaustive
import com.avtograv.model.Movie
import kotlinx.coroutines.launch

internal class MoviesListViewModelImpl(private val repository: MovieRepository) : MoviesListViewModel() {

    override val moviesStateOutput = MutableLiveData<MoviesListViewState>()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch { handleResult(repository.loadMovies()) }
    }

    private fun handleResult(result: Result<List<Movie>>) {
        when (result) {
            is Success -> moviesStateOutput.postValue(MoviesListViewState.MoviesLoaded(result.data))
            is Failure -> moviesStateOutput.postValue(MoviesListViewState.FailedToLoad(result.exception))
        }.exhaustive
    }
}