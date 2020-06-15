package asywalul.movie.test.screen.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import asywalul.movie.test.R
import asywalul.movie.test.common.ViewState
import asywalul.movie.test.data.local.entity.Detail
import asywalul.movie.test.data.local.entity.Discover
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.UpComing
import asywalul.movie.test.viewmodel.MovieViewModel
import asywalul.movie.test.utils.Constant
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_information.*
import kotlinx.android.synthetic.main.layout_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieInformationFragment : Fragment() {

    private var upComing :UpComing? =null
    var popular :Popular? =null
    private var discover : Discover? =null
    private val movieViewModel by viewModel<MovieViewModel>()
    private var type : Int? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                discover= bundle.getSerializable("movie") as Discover?
            }
        }

        movieViewModel.movieDetailState.observe(viewLifecycleOwner, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                ViewState.State.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    setInformation(it.data)
                }
                ViewState.State.FAILED -> {
                    progress_bar.visibility = View.GONE
                    it.err?.let { err -> observeError(err) }
                }
            }
        })

        when (type) {
            1 -> {
                movieViewModel.getMoviesDetail(upComing?.id.toString())
            }
            2 -> {
                movieViewModel.getMoviesDetail(popular?.id.toString())
            }
            else -> {
                movieViewModel.getMoviesDetail(discover?.id.toString())
            }
        }

    }

    private fun setInformation(result:Detail?){
        Picasso.get()
            .load(Constant.API.HOST_IMAGE+result?.posterPath)
            .into(ivMovie, object : Callback {
                override fun onSuccess() {
                   /// progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception) {
                   // progressBar.visibility = View.VISIBLE
                }
            })

        tvTitle.text = result?.title
        tvImb.text = result?.imdbId
        tvVote.text = result?.voteAverage.toString()
        tvDescription.text = result?.overview
    }

    private fun observeError(err: Throwable) {
        Log.d("error movie",err.message.toString())
    }

}
