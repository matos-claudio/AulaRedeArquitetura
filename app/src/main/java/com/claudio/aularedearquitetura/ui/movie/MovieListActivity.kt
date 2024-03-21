package com.claudio.aularedearquitetura.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.claudio.aularedearquitetura.databinding.ActivityMovieListBinding

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getMovies()
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            movieAdapter.setMovieList(movieList)
        })
    }

    fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = movieAdapter
//            movieAdapter.notifyDataSetChanged()
        }
    }
}