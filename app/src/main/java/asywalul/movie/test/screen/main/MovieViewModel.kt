package asywalul.movie.test.screen.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import asywalul.movie.test.base.BaseViewModel
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.domain.SchedulerProviders
import asywalul.movie.test.domain.idlingresource.EspressoIdlingResource
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.UpComing
import asywalul.movie.test.model.repository.MovieRepositoryImpl
import java.util.concurrent.TimeUnit

class MovieViewModel(private val movieRepositoryImpl: MovieRepositoryImpl, private var scheduler: SchedulerProviders) : BaseViewModel(){

    val moviePopularListState = MutableLiveData<ViewState<List<Popular>>>()
    val movieUpComingListState = MutableLiveData<ViewState<List<UpComing>>>()


    @SuppressLint("CheckResult")
    fun getMoviesPopular() {
        EspressoIdlingResource.increment()
        movieRepositoryImpl.getMoviePopular()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .delay(2, TimeUnit.SECONDS)
            .doOnNext {
                moviePopularListState.postValue(ViewState.loading())
            }
            .subscribe({
                moviePopularListState.postValue(ViewState.success(it))
                EspressoIdlingResource.decrement()
            }, {
                moviePopularListState.postValue(ViewState.error(it))
                EspressoIdlingResource.decrement()
            }
            ).also { compositeDisposable.add(it) }
    }

    @SuppressLint("CheckResult")
    fun getMoviesUpComing() {
        EspressoIdlingResource.increment()
        movieRepositoryImpl.getMovieUpComing()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .delay(2, TimeUnit.SECONDS)
            .doOnNext {
                movieUpComingListState.postValue(ViewState.loading())

            }
            .subscribe({
                movieUpComingListState.postValue(ViewState.success(it))
                EspressoIdlingResource.decrement()
            }, {
                movieUpComingListState.postValue(ViewState.error(it))
                EspressoIdlingResource.decrement()
            }
            ).also { compositeDisposable.add(it) }
    }

}