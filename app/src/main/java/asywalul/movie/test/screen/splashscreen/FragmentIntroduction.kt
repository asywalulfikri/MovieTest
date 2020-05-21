package asywalul.movie.test.screen.splashscreen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import asywalul.movie.test.R
import asywalul.movie.test.base.BaseFragment
import asywalul.movie.test.screen.main.MovieListActivity
import kotlinx.android.synthetic.main.fragment_intro.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentIntroduction : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnNext.setOnClickListener {
            if(validation()){
                saveData()
                startActivity(Intent(activity, MovieListActivity::class.java))
            }
        }

        etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(validation()){
                    ivCorrect.visibility = View.VISIBLE
                }else{
                    ivCorrect.visibility = View.GONE
                }
            }
        })

        ivCorrect.setOnClickListener {
            linear2.visibility = View.VISIBLE
            tvValidation.visibility = View.GONE
            etName.isEnabled = false
            ivCorrect.visibility = View.GONE
            tvWelcome.text = "Nice to meet you "+ etName.text.toString() +" !Now you can continue"
        }

    }

    private fun saveData(){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString("name", etName.text.toString())
        editor.putBoolean("isLogin",true)

        editor.apply()
    }

    private fun validation():Boolean{
        return etName.text.toString().trim().length >= 6
    }

    private fun capitalize(capString: String): Boolean {
        val capMatcher: Matcher = Pattern.compile(".*[A-Z].*", Pattern.CASE_INSENSITIVE).matcher(capString)
        while (capMatcher.find()) {
            return true
        }
        return true
    }
}
