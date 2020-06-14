package asywalul.movie.test.screen.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import asywalul.movie.test.R
import asywalul.movie.test.base.BaseActivity
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.data.local.entity.Genres
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.UpComing
import asywalul.movie.test.screen.detail.MovieDetailActivity
import asywalul.movie.test.screen.list.MovieGenreActivity
import asywalul.movie.test.screen.main.adapter.GenreAdapter
import asywalul.movie.test.screen.main.adapter.MovieAdapter
import asywalul.movie.test.screen.main.adapter.MovieSliderAdapter
import asywalul.movie.test.screen.splashscreen.SplashScreenActivity
import asywalul.movie.test.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


class MovieListActivity : BaseActivity(), MovieAdapter.OnClickListener {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var movieAdapter : MovieAdapter
    private lateinit var genreAdapter : GenreAdapter
    private var resultUpComing: List<UpComing> = ArrayList()
    private var resultGenre: List<Genres> = ArrayList()
    private var resultUpPopular: List<Popular> = ArrayList()
    private lateinit var movieSliderAdapter: MovieSliderAdapter
    private var timer: Timer? = null
    private var current_possition = 0
    var custom_Position = 0
    var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.run {
            layoutManager = GridLayoutManager(context,2)
            isNestedScrollingEnabled = true
            setHasFixedSize(true)
            movieAdapter = MovieAdapter()
            adapter = movieAdapter
        }

        recyclerViewGenre.run {
            layoutManager = GridLayoutManager(context,3)
            isNestedScrollingEnabled = true
            setHasFixedSize(true)
            genreAdapter = GenreAdapter()
            adapter = genreAdapter
        }

        observeData()
        movieViewModel.getMoviesUpComing()
        movieViewModel.getMoviesPopular()
        movieViewModel.getMoviesGenres()

        updateUser()

    }

    private fun updateUser(){
        tvName.text = setDay()+" "+mSharedPref.getString("name","NULL")
        tvLogOut.setOnClickListener {
            clearUser()
            startActivity(Intent(this,SplashScreenActivity::class.java))
            finish()
        }
    }


    private fun observeData() {

        movieViewModel.movieUpComingListState.observe(this, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {

                }
                ViewState.State.SUCCESS -> {

                    observeMovies(it.data)
                }
                ViewState.State.FAILED -> {
                    it.err?.let { err -> observeError(err) }
                }
            }
        })

        movieViewModel.moviePopularListState.observe(this, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                ViewState.State.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    setupBanner(it.data)
                }
                ViewState.State.FAILED -> {
                    progress_bar.visibility = View.GONE
                    it.err?.let { err -> observeError(err) }
                }
            }
        })

        movieViewModel.movieGenresState.observe(this, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                ViewState.State.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    observeGenre(it.data)
                }
                ViewState.State.FAILED -> {
                    progress_bar.visibility = View.GONE
                    it.err?.let { err -> observeError(err) }
                }
            }
        })

    }

    private fun observeError(err: Throwable) {
        Log.d("error movie",err.message.toString())
    }


    private fun observeGenre(result : List<Genres>?) {
        this.resultGenre = result!!
        genreAdapter.setItems(result)
    }

    private fun observeMovies(result: List<UpComing>?) {
        this.resultUpComing = result!!
        movieAdapter.setItems(result,this)
    }



    private fun setupBanner(result : List<Popular>?){
        this.resultUpPopular = result!!


        movieSliderAdapter = MovieSliderAdapter(this,result)
        viewPager.adapter = movieSliderAdapter

        prepareDots(custom_Position++);
        createSliderShow();
    }


    private fun createSliderShow() {
        val handler = Handler()
        val runnable = Runnable {
            if (current_possition == Int.Companion.MAX_VALUE) current_possition = 0
            viewPager.setCurrentItem(current_possition++, true)
        }
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 2500, 2500)


        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (custom_Position > resultUpPopular.size - 1) custom_Position = 0
                prepareDots(custom_Position++)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }


    private fun prepareDots(current_Slider_Position: Int) {
        if (dotsLayoute.childCount > 0) dotsLayoute.removeAllViews()
        val dots: Array<ImageView?> = arrayOfNulls(resultUpPopular.size)
        for (i in resultUpPopular.indices) {
            dots[i] = ImageView(this)
            if (i == current_Slider_Position) dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.dot_select
                )
            ) else dots[i]!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_default))
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(4, 0, 4, 0)
            dotsLayoute.addView(dots[i], layoutParams)
        }
    }

    override fun onClick(position: Int) {
        val upComing = resultUpComing[position]
        val intent = Intent(this,MovieGenreActivity::class.java)
        intent.putExtra("type",1)
        intent.putExtra("movie", upComing)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true)
            return
        }

        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press 1 More", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)

    }


}
