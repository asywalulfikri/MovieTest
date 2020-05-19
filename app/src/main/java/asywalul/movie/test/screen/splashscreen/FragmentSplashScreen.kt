package asywalul.movie.test.screen.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import asywalul.movie.test.BuildConfig
import asywalul.movie.test.R
import kotlinx.android.synthetic.main.fragment_splash_screeen.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentSplashScreen : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screeen, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvVersion.text = getString(R.string.version)+ BuildConfig.VERSION_NAME

    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splash_to_intro)
        }, 2000)
    }
}
