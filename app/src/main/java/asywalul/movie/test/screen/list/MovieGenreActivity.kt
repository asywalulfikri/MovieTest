package asywalul.movie.test.screen.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asywalul.movie.test.R
import asywalul.movie.test.base.BaseActivity
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.screen.detail.MovieDetailActivity
import asywalul.movie.test.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_list_genre.*
import kotlinx.android.synthetic.main.layout_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieGenreActivity : BaseActivity(), DiscoverAdapter.OnClickListener {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var discoverAdapter : DiscoverAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var genreId : Int = 0
    private var isLoadMore : Boolean = false
    private var maxPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_genre)


        genreId = intent.getIntExtra("genreId",0)

        linearLayoutManager = LinearLayoutManager(this)
        discoverAdapter = DiscoverAdapter()

        val scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (page < maxPage) {
                    isLoadMore = true
                    movieViewModel.getMoviesDiscover(page,genreId)
                }else{
                    isLoadMore = false
                }
            }

        }

        recyclerView.run {
            layoutManager = linearLayoutManager
            adapter = discoverAdapter
            addOnScrollListener(scrollListener)
        }

        observeData()
        movieViewModel.getMoviesDiscover(1,genreId)

    }


    private fun observeData() {

        movieViewModel.movieDiscoverState.observe(this, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                ViewState.State.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    maxPage = it.data!!.totalPages!!

                    if(isLoadMore){
                        discoverAdapter.addItem(it.data.results)
                    }else{
                        discoverAdapter.setItems(it.data.results,this)
                    }
                }
                ViewState.State.FAILED -> {
                    it.err?.let { err -> observeError(err) }
                }
            }
        })

    }

    private fun observeError(err: Throwable) {
        Log.d("error movie",err.message.toString())
    }


    override fun onClick(position: Int) {
        val discover = discoverAdapter.getList()[position]
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("type",3)
        intent.putExtra("movie", discover)
        startActivity(intent)
    }
}
