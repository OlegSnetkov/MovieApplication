package com.avtograv.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avtograv.data.remote.retrofit.ImageUrlAppender
import com.avtograv.data.MovieRepositoryImpl
import com.avtograv.data.remote.retrofit.RetrofitDataSource
import com.avtograv.di.MovieRepositoryProvider
import com.avtograv.di.NetworkModule
import com.avtograv.domain.MovieRepository
import com.avtograv.features.moviedetails.view.MovieDetailsFragment
import com.avtograv.features.movies.view.MoviesListFragment
import com.avtograv.movieapplication.R
import kotlinx.serialization.ExperimentalSerializationApi

internal class MainActivity : AppCompatActivity(),
    MoviesListFragment.MoviesListItemClickListener,
    MovieDetailsFragment.MovieDetailsBackClickListener,
    MovieRepositoryProvider {

    private val networkModule = NetworkModule()

    @ExperimentalSerializationApi
    private val remoteDataSource =
        RetrofitDataSource(networkModule.api, ImageUrlAppender(networkModule.api))

    @ExperimentalSerializationApi
    private val movieRepository = MovieRepositoryImpl(remoteDataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun onMovieSelected(movieId: Int) {
        routeToMovieDetails(movieId)
    }

    override fun onMovieDeselected() {
        routeBack()
    }

    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                MoviesListFragment.create(),
                MoviesListFragment::class.java.simpleName
            )
            .commit()
    }

    private fun routeToMovieDetails(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                MovieDetailsFragment.create(movieId),
                MovieDetailsFragment::class.java.simpleName
            )
            .addToBackStack("trans:${MovieDetailsFragment::class.java.simpleName}")
            .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository = movieRepository
}