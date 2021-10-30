package com.avtograv.domain

import com.avtograv.common.model.Result
import com.avtograv.model.Movie
import com.avtograv.model.MovieDetails


internal interface MovieRepository {

    suspend fun loadMovies(): Result<List<Movie>>
    suspend fun loadMovie(movieId: Int): Result<MovieDetails>
}