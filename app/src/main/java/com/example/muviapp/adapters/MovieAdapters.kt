package com.example.muviapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.muviapp.MovieDetailsActivity
import com.example.muviapp.databinding.ItemPopularMoviesBinding
import com.example.muviapp.models.entities.RandomMovies
import com.example.muviapp.utils.Constants

class MovieAdapters(private val context:Context):RecyclerView.Adapter<MovieAdapters.ViewHolder>() {

    var movieList:List<RandomMovies.Result> = listOf()

    class ViewHolder(itemView:ItemPopularMoviesBinding):RecyclerView.ViewHolder(itemView.root) {

        val movieTitle=itemView.textName
        val movieLanguage=itemView.textLanguage
        val movieReleaseDate=itemView.textReleaseDate
        val moviePopularity=itemView.textPopularity
        val moviePoster=itemView.imageTvShow

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemPopularMoviesBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=movieList[position]
        holder.movieTitle.text=item.title
        holder.movieLanguage.text=item.original_language
        holder.movieReleaseDate.text=item.release_date
        holder.moviePopularity.text= item.popularity.toString()
        Glide.with(context).load(Constants.IMAGE_PATH+item.poster_path).into(holder.moviePoster)

        holder.itemView.setOnClickListener {
            val intent=Intent(context,MovieDetailsActivity::class.java)
            intent.putExtra("title",item.title)
            intent.putExtra("language",item.original_language)
            intent.putExtra("popularity",item.popularity.toString())
            intent.putExtra("releaseDate",item.release_date)
            intent.putExtra("description",item.overview)
            intent.putExtra("rating",item.vote_count.toString())
            intent.putExtra("posterPath",item.poster_path)
            intent.putExtra("backdropPath",item.backdrop_path)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
    fun movies(list: List<RandomMovies.Result>){
        movieList=list
    }
}