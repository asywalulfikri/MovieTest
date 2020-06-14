package asywalul.movie.test.intface.idlingresource

import android.util.Log
import androidx.test.espresso.idling.CountingIdlingResource


object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val idlingResource = CountingIdlingResource(RESOURCE)
    fun increment() {
        loggingError("Idle", "Increment")
        idlingResource.increment()
    }

    fun decrement() {
        loggingError("Idle", "decrement")
        if (!idlingResource.isIdleNow) {
            idlingResource.decrement()
        }
    }

    private fun loggingError(tag : String, msg : String){
        Log.e(tag,msg)
    }

}
