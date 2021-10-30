package com.avtograv.data

import com.avtograv.common.model.Result
import com.avtograv.common.model.runCatchingResult
import com.avtograv.data.remote.RemoteDataSource
import com.avtograv.domain.MovieRepository
import com.avtograv.model.Movie
import com.avtograv.model.MovieDetails


internal class MovieRepositoryImpl(
    private val remoteDataResource: RemoteDataSource,
) : MovieRepository {
    override suspend fun loadMovies(): Result<List<Movie>> {
        return runCatchingResult { remoteDataResource.loadMovies() }
    }

    override suspend fun loadMovie(movieId: Int): Result<MovieDetails> {
        return runCatchingResult { remoteDataResource.loadMovie(movieId) }
    }
}