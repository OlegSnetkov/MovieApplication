package com.avtograv.data.remote

import com.avtograv.model.Movie
import com.avtograv.model.MovieDetails

interface RemoteDataSource {

    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}