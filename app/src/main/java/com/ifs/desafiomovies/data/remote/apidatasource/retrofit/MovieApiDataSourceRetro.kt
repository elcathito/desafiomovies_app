package com.ifs.desafiomovies.data.remote.apidatasource.retrofit


import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.data.exception.ResponseError
import com.ifs.desafiomovies.data.remote.model.MovieResponse
import com.ifs.desafiomovies.data.remote.model.ResultGenreResponse
import com.ifs.desafiomovies.data.remote.apidatasource.MovieApiDataSource
import com.ifs.desafiomovies.data.remote.helper.safeCall
import com.ifs.desafiomovies.data.remote.service.MovieService
import com.ifs.desafiomovies.domain.model.Genre
import com.ifs.desafiomovies.domain.model.Movie
import javax.inject.Inject

class MovieApiDataSourceRetro @Inject constructor(
    private val movieService: MovieService
) : MovieApiDataSource {

    override suspend fun getMovie(): Either<Movie, Exception> {
        return when (val response = safeCall {movieService.getMovie()}) {
            is MovieResponse -> {
                Either.Success(data = response.toData())
            }
            else -> {
                Either.Failure(response as ResponseError)
            }
        }
    }

    override suspend fun getAllGenres(): Either<List<Genre>, Exception>{
        return when (val response = safeCall {movieService.getAllGenres()}) {
            is ResultGenreResponse -> {
                Either.Success(data =  response.genres.map { it.toData() })
            }
            else -> {
                Either.Failure(response as ResponseError)
            }
        }
    }
}