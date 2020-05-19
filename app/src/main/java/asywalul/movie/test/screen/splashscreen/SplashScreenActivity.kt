package asywalul.movie.test.screen.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asywalul.movie.test.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
