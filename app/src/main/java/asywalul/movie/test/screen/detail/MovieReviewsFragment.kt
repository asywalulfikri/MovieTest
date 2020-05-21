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
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.data.local.entity.UpComing
import asywalul.movie.test.screen.detail.adapter.ReviewsAdapter
import asywalul.movie.test.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_review.*
import kotlinx.android.synthetic.main.layout_empty.*
import kotlinx.android.synthetic.main.layout_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieReviewsFragment : Fragment() {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var reviewAdapter : ReviewsAdapter
    var upComing :UpComing? =null
    var popular :Popular? =null
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
            reviewAdapter = ReviewsAdapter()
            adapter = reviewAdapter
        }

        val bundle = this.arguments
        type = bundle!!.getSerializable("type") as Int

        if(type==1){
            upComing = bundle!!.getSerializable("movie") as UpComing?
        }else{
            popular= bundle!!.getSerializable("movie") as Popular?
        }

        movieViewModel.movieReviewState.observe(viewLifecycleOwner, Observer {
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


        if(type==1){
            movieViewModel.getMoviesReviews(upComing?.id.toString())
        }else{
            movieViewModel.getMoviesReviews(popular?.id.toString())
        }


    }


    private fun observeError(err: Throwable) {
        Log.d("error movie",err.message.toString())
    }


    private fun observeMovies(result: List<Reviews>?) {
        reviewAdapter.setItems(result!!)
    }


}
