package asywalul.movie.test.screen.splashscreen

import android.os.Bundle
import asywalul.movie.test.R
import asywalul.movie.test.base.BaseActivity
import kotlin.system.exitProcess

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        exitProcess(1);
    }
}
