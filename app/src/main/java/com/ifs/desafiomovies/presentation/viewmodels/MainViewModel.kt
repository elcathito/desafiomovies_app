package com.ifs.desafiomovies.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.data.exception.ResponseError
import com.ifs.desafiomovies.domain.model.Movie
import com.ifs.desafiomovies.domain.usecases.*
import com.ifs.desafiomovies.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovie: GetMovie,
    private val getSimilarMovies: GetSimilarMovies,
    private val favoriteMovie: FavoriteMovie,
    private val disfavorMovie: DisfavorMovie,
    private val isFavoriteMovie: IsFavoriteMovie
): ViewModel(){


    fun getMovieDetail(){

    }

    fun favorite() = favoriteMovie()
    fun disfavor() = disfavorMovie()
    fun isFavorite():Boolean = isFavoriteMovie()

    suspend fun moviesSimilarPaging() = getSimilarMovies.invoke().cachedIn(viewModelScope)

}