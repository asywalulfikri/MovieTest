package asywalul.movie.test.app.di

import androidx.room.Room
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.data.local.source.LocalDataSourceImpl
import asywalul.movie.test.data.remote.source.RemoteDataSourceImpl
import asywalul.movie.test.intface.SchedulerProviders
import asywalul.movie.test.data.local.AppDatabase
import asywalul.movie.test.model.api.MovieApi
import asywalul.movie.test.model.repository.MovieRepositoryImpl
import asywalul.movie.test.screen.detail.MovieInformationFragment
import asywalul.movie.test.screen.detail.MovieReviewsFragment
import asywalul.movie.test.utils.Constant
import asywalul.movie.test.viewmodel.MovieViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    viewModel { MovieViewModel(get(), get()) }

    single { SchedulerProviders.DEFAULT }

    single { MovieRepositoryImpl(get(), get()) }

    fragment { MovieInformationFragment() }
    fragment { MovieReviewsFragment() }



    single { LocalDataSourceImpl(get(), get()) }
    single { RemoteDataSourceImpl(get(), get(), get()) }

    single { ViewState }
    single {
        return@single OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
                .baseUrl(Constant.API.HOST)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    single {
        createApiService<MovieApi>(get())

    }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "movie_db").allowMainThreadQueries().build()
    }

    single {
        get<AppDatabase>().movieDao
    }


}

inline fun <reified T> createApiService(retrofit: Retrofit): T = retrofit.create(T::class.java)