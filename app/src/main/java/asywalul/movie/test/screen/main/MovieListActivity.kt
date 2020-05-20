package asywalul.movie.test.screen.main
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import asywalul.movie.test.R
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.UpComing
import asywalul.movie.test.screen.detail.MovieDetailActivity
import asywalul.movie.test.screen.main.adapter.MovieAdapter
import asywalul.movie.test.screen.main.adapter.MovieSliderAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


class MovieListActivity : AppCompatActivity(), MovieAdapter.OnClickListener {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var movieAdapter : MovieAdapter

    private var images: ArrayList<String> = ArrayList()
    private var timer: Timer? = null
    private var currentImage = 0
    private val sliderDelay: Long = 2500.toLong()
    private val sliderGap: Long = 2500.toLong()
    private var isRegisteredSliderImageChangeListener: Boolean = false
    private var result: List<UpComing> = ArrayList()

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

        observeData()
        movieViewModel.getMoviesUpComing()
        movieViewModel.getMoviesPopular()

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

                }
                ViewState.State.SUCCESS -> {

                    setupBanner(it.data)
                }
                ViewState.State.FAILED -> {
                    it.err?.let { err -> observeError(err) }
                }
            }
        })

    }

    private fun observeError(err: Throwable) {
        Log.d("error movie",err.message)
    }


    private fun observeMovies(result: List<UpComing>?) {
        this.result = result!!
        movieAdapter.setItems(result!!,this)
    }



    private fun setupBanner(result : List<Popular>?){

        var sliderAdapter = result?.let { MovieSliderAdapter(it) }
        viewPager.adapter = sliderAdapter
        if (images.size > 1) {
            tabs_dot_indicator.setupWithViewPager(viewPager, true)
            viewPager.addOnPageChangeListener(sliderImageChangeListener)
            isRegisteredSliderImageChangeListener = true

            // Auto Change of Images
            scheduleImageSlider()
        } else {
           // tabs_dot_indicator.visibility = View.GONE
        }
    }


    private fun scheduleImageSlider() {
        val handler = Handler(Looper.getMainLooper())
        val updateImageRunnable = Runnable {
            viewPager.let {
                currentImage = if (currentImage == images.size) 0 else ++currentImage
                viewPager.setCurrentItem(currentImage, true)
            }
        }
        timer?.cancel() // Cancelling if timer already exists...
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(updateImageRunnable)
            }
        }, sliderDelay, sliderGap)
    }

    private val sliderImageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            currentImage = position
            scheduleImageSlider() // Rescheduling Image Slider....
        }

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    }

    override fun onNoteClick(position: Int) {
        val upComing = result[position]
        val intent = Intent(this,MovieDetailActivity::class.java)
        intent.putExtra("movie", upComing)
        startActivity(intent)
    }

}
