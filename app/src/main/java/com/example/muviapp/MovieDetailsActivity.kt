package com.example.muviapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.muviapp.databinding.ActivityMovieDetailsBinding
import com.example.muviapp.utils.Constants

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title=intent.getStringExtra("title")
        val language=intent.getStringExtra("language")
        val popularity=intent.getStringExtra("popularity")
        val releaseDate=intent.getStringExtra("releaseDate")
        val description=intent.getStringExtra("description")
        val rating=intent.getStringExtra("rating")
        val posterPath=intent.getStringExtra("posterPath")
        val backdropPath=intent.getStringExtra("backdropPath")

        binding.textName.text=title
        binding.textLanguage.text=language
        binding.textPopularity.text=popularity
        binding.textStartedDate.text=releaseDate
        binding.textDescription.text=description
        binding.textRating.text=rating
        Glide.with(this).load(Constants.IMAGE_PATH+posterPath).into(binding.imageTvShow)
        Glide.with(this).load(Constants.IMAGE_PATH+backdropPath).into(binding.sliderViewPager)

    }
}