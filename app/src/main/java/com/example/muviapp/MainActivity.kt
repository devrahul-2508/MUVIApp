package com.example.muviapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.muviapp.adapters.MovieAdapters
import com.example.muviapp.databinding.ActivityMainBinding
import com.example.muviapp.viewModels.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapters: MovieAdapters
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel=ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMoviesFromApi()
        movieAdapters= MovieAdapters(this)
        binding.idRVMovies.layoutManager=LinearLayoutManager(this)
        binding.idRVMovies.adapter=movieAdapters
        observeMovies()

    }

    private fun observeMovies() {
        binding.pbLoading.visibility=View.GONE
        movieViewModel.movieResponse.observe(this){
            movies->
              Log.i("Movies","${movies.results}")
               movieAdapters.movies(movies.results)
               movieAdapters.notifyDataSetChanged()

        }
    }
}