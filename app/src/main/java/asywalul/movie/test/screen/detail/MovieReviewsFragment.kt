package asywalul.movie.test.screen.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import asywalul.movie.test.R
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.data.local.entity.Reviews
import asywalul.movie.test.screen.detail.adapter.ReviewsAdapter
import asywalul.movie.test.screen.main.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieReviewsFragment : Fragment() {

    private val movieViewModel by viewModel<MovieViewModel>()
    private lateinit var reviewAdapter : ReviewsAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.run {
            layoutManager = GridLayoutManager(context,2)
            isNestedScrollingEnabled = true
            setHasFixedSize(true)
            reviewAdapter = ReviewsAdapter()
            adapter = reviewAdapter
        }

        observeData()
        movieViewModel.getMoviesReviews("228165")
    }



    private fun observeData() {

        movieViewModel.movieReviewListState.observe(viewLifecycleOwner, Observer {
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



    }

    private fun observeError(err: Throwable) {
        Log.d("error movie",err.message)
    }


    private fun observeMovies(result: List<Reviews>?) {
        reviewAdapter.setItems(result!!)
    }


}
