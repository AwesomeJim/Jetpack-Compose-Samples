package com.awesomejim.popularmovies.network

import com.awesomejim.popularmovies.BuildConfig.MOVIE_API_KEY
import com.awesomejim.popularmovies.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/popular?api_key=${MOVIE_API_KEY}&language=en-US")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse
}