package asywalul.movie.test.screen.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import asywalul.movie.test.R
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.data.local.entity.*
import asywalul.movie.test.screen.detail.adapter.VideoAdapter
import asywalul.movie.test.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_review.*
import kotlinx.android.synthetic.main.layout_empty.*
import kotlinx.android.synthetic.main.layout_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieVideoFragment : Fragment() {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var videoAdapter : VideoAdapter
    var upComing :UpComing? =null
    var popular :Popular? =null
    var discover :Discover? = null
    var type : Int? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = true
            setHasFixedSize(true)
            videoAdapter = VideoAdapter()
            adapter = videoAdapter
        }

        val bundle = this.arguments
        type = bundle!!.getSerializable("type") as Int

        when (type) {
            1 -> {
                upComing = bundle.getSerializable("movie") as UpComing?
            }
            2 -> {
                popular= bundle.getSerializable("movie") as Popular?
            }
            else -> {
                discover = bundle.getSerializable("movie") as Discover?
            }
        }

        movieViewModel.movieVideoState.observe(viewLifecycleOwner, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                ViewState.State.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if(it.data?.results?.isEmpty()!!){
                        layout_empty.visibility =View.VISIBLE
                    }else{
                        observeMovies(it.data.results)
                    }

                }
                ViewState.State.FAILED -> {
                    progress_bar.visibility = View.GONE
                    it.err?.let { err -> observeError(err) }
                }
            }
        })


        when (type) {
            1 -> {
                movieViewModel.getMoviesVideo(upComing?.id.toString())
            }
            2 -> {
                movieViewModel.getMoviesVideo(popular?.id.toString())
            }
            else -> {
                movieViewModel.getMoviesVideo(discover?.id.toString())
            }
        }


    }


    private fun observeError(err: Throwable) {
        Log.d("error movie",err.message.toString())
    }


    private fun observeMovies(result: List<Videos>?) {
        activity?.let { videoAdapter.setItems(result!!, it) }
    }


}
