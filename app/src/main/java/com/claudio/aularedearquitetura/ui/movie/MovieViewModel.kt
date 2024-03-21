package com.claudio.aularedearquitetura.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.claudio.aularedearquitetura.model.Movie
import com.claudio.aularedearquitetura.model.Result
import com.claudio.aularedearquitetura.services.IMovieService
import com.claudio.aularedearquitetura.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result>>()

    fun getMovies() {
        RetrofitClient.createService(IMovieService::class.java).getMovies("69d66957eebff9666ea46bd464773cf0")
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if(response.isSuccessful) {
                        movieLiveData.value = response.body()!!.results
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("MovieViewModel", t.message.toString())
                }
            })
    }

    fun observeMovieLiveData(): LiveData<List<Result>> {
        return movieLiveData
    }
}