package com.avtograv.model

import com.avtograv.model.Actor
import com.avtograv.model.Genre

data class MovieDetails(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val detailImageUrl: String?,
    val storyLine: String,
    val actors: List<Actor>
)