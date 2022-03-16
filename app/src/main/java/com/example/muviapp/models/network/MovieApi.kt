package com.example.muviapp.models.network

import com.example.muviapp.models.entities.RandomMovies
import com.example.muviapp.utils.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(Constants.END_POINT)
    fun getMovies(
        @Query(Constants.API_KEY) api_key:String,
        @Query(Constants.LANGUAGE) language:String,
        @Query(Constants.PAGE) page:Int
    ):Observable<RandomMovies.Movies>

}