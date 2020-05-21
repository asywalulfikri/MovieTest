package asywalul.movie.test.base

import android.content.SharedPreferences
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    val sharedPreferences: SharedPreferences get() = (activity as BaseActivity?)!!.getSharedPreferences()!!
}