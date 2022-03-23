package com.example.muviapp.models.network

import com.example.muviapp.models.entities.RandomMovies
import com.example.muviapp.utils.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {

    private val api=Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(MovieApi::class.java)


    fun getPopularMovies(page:Int): Observable<RandomMovies.Movies> {
        return api.getMovies(
            Constants.API_KEY_VALUE,
            Constants.LANGUAGE_VALUE,
            page
        )
    }
}
