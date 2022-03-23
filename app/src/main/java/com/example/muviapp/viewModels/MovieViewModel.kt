package com.example.muviapp.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.muviapp.models.entities.RandomMovies
import com.example.muviapp.models.network.MovieService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieViewModel:ViewModel() {

    private val movieService=MovieService()

    private val compositeDisposable=CompositeDisposable()

    val movieResponse=MutableLiveData<RandomMovies.Movies>()

    fun getMoviesFromApi(page:Int){
        compositeDisposable.add(
            movieService.getPopularMovies(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ response->getObserver(response as RandomMovies.Movies)},
            {t->onFailure(t)}
        ))

    }

    private fun onFailure(t: Throwable) {
        Log.d("main", "onFailure: "+t.message)
    }

    private fun getObserver(movie: RandomMovies.Movies) {
            movieResponse.value=movie
    }
}
