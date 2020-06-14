package asywalul.movie.test.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import asywalul.movie.test.base.BaseViewModel
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.intface.SchedulerProviders
import asywalul.movie.test.intface.idlingresource.EspressoIdlingResource
import asywalul.movie.test.model.repository.MovieRepositoryImpl
import asywalul.movie.test.model.response.DiscoverResponse
import java.util.concurrent.TimeUnit

class MovieViewModel(private val movieRepositoryImpl: MovieRepositoryImpl, private var scheduler: SchedulerProviders) : BaseViewModel(){

    val moviePopularListState  = MutableLiveData<ViewState<List<Popular>>>()
    val movieUpComingListState = MutableLiveData<ViewState<List<UpComing>>>()
    val movieDetailState       = MutableLiveData<ViewState<Detail>>()
    val movieReviewState   = MutableLiveData<ViewState<ReviewsResponse>>()
    val movieGenresState   = MutableLiveData<ViewState<List<Genres>>>()
    val movieDiscoverState   = MutableLiveData<ViewState<DiscoverResponse>>()

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


    fun getMoviesReviews(idMovie : String) {
        EspressoIdlingResource.increment()
        movieRepositoryImpl.getMovieReviews(idMovie)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .delay(2, TimeUnit.SECONDS)
            .doOnNext {
                movieReviewState.postValue(ViewState.loading())
            }
            .subscribe({
                movieReviewState.postValue(ViewState.success(it))
                EspressoIdlingResource.decrement()
            }, {
                movieReviewState.postValue(ViewState.error(it))
                EspressoIdlingResource.decrement()
            }
            ).also { compositeDisposable.add(it) }
    }

    fun getMoviesGenres() {
        EspressoIdlingResource.increment()
        movieRepositoryImpl.getMovieGenres()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .delay(2, TimeUnit.SECONDS)
            .doOnNext {
                movieGenresState.postValue(ViewState.loading())
            }
            .subscribe({
                movieGenresState.postValue(ViewState.success(it))
                EspressoIdlingResource.decrement()
            }, {
                movieGenresState.postValue(ViewState.error(it))
                EspressoIdlingResource.decrement()
            }
            ).also { compositeDisposable.add(it) }
    }


    fun getMoviesDiscover(page :Int,genreId: Int) {
        EspressoIdlingResource.increment()
        movieRepositoryImpl.getMovieDiscover(page,genreId)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .delay(2, TimeUnit.SECONDS)
            .doOnNext {
                movieDiscoverState.postValue(ViewState.loading())
            }
            .subscribe({
                movieDiscoverState.postValue(ViewState.success(it))
                EspressoIdlingResource.decrement()
            }, {
                movieDiscoverState.postValue(ViewState.error(it))
                EspressoIdlingResource.decrement()
            }
            ).also { compositeDisposable.add(it) }
    }


    fun getMoviesDetail(id: String) {
        EspressoIdlingResource.increment()
        movieRepositoryImpl.getMovieDetail(id)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .delay(2, TimeUnit.SECONDS)
            .doOnNext {
                movieDetailState.postValue(ViewState.loading())
            }
            .subscribe(
                {
                    movieDetailState.postValue(ViewState.success(it))
                    EspressoIdlingResource.decrement()
                }, {
                    movieDetailState.postValue(ViewState.error(it))
                    EspressoIdlingResource.decrement()
                }
            ).also { compositeDisposable.add(it) }
    }

}