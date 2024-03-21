package com.claudio.aularedearquitetura.services

import com.claudio.aularedearquitetura.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieService {
    @GET("popular?")
    fun getMovies(@Query("api_key") apiKey: String):Call<Movie>
}