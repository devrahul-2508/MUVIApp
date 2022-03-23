package com.example.muviapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muviapp.adapters.MovieAdapters
import com.example.muviapp.databinding.ActivityMainBinding
import com.example.muviapp.models.entities.RandomMovies
import com.example.muviapp.viewModels.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapters: MovieAdapters
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private val movieList: ArrayList<RandomMovies.Result> = ArrayList()

    var currentPage:Int=1
    var totalAvailablePages:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel=ViewModelProvider(this).get(MovieViewModel::class.java)
        movieAdapters= MovieAdapters(this)
        layoutManager= LinearLayoutManager(this)
        binding.idRVMovies.layoutManager=layoutManager
        binding.idRVMovies.adapter=movieAdapters

        //Pagination
        binding.idRVMovies.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //TODO: can't get below checkpoint.
                if (!binding.idRVMovies.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1
                        fetchMovies()
                    }
                }
            }
        })
        fetchMovies()


    }

    private fun fetchMovies() {
        movieViewModel.getMoviesFromApi(currentPage)
        binding.pbLoading.visibility=View.GONE
        movieViewModel.movieResponse.observe(this){
            movies->
                movies.let {
                    totalAvailablePages=movies.total_pages
                    var oldCount=movieList.size
                    movieList.addAll(movies.results)
                    movieAdapters.movies(movieList)
                    movieAdapters.notifyItemRangeInserted(oldCount, movieList.size)


                }



        /* Log.i("Movies","${movies.results}")
              movieList.addAll(movies.results)
               movieAdapters.movies(movieList)
               movieAdapters.notifyDataSetChanged()*/



        }
    }
}
