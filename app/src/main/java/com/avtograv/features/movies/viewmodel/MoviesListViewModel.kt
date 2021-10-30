package com.avtograv.features.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

internal abstract class MoviesListViewModel : ViewModel() {

    abstract val moviesStateOutput: LiveData<MoviesListViewState>
}