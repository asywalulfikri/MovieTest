package asywalul.movie.test.base

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class BaseActivity : AppCompatActivity() {
    lateinit var mSharedPref: SharedPreferences

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        mSharedPref = getSharedPreferences("user", Context.MODE_PRIVATE)

    }

    open fun getSharedPreferences(): SharedPreferences? {
        return mSharedPref
    }
    
    fun setDay() : String{
        var day = "Day"
        val cal: Calendar = Calendar.getInstance()
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
        day = when (hour) {
            in 19..0 -> {
                "Night"
            }
            in 2..9 -> {
                "Morning"
            }
            else -> {
                "Afternoon"
            }
        }
        return day
    }

    fun clearUser(){
        val editor: SharedPreferences.Editor = mSharedPref.edit()

        editor.putString("name", "")
        editor.putBoolean("isLogin",false)

        editor.apply()
    }


}