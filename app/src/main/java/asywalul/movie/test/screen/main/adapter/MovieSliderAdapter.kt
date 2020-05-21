package asywalul.movie.test.screen.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import asywalul.movie.test.R
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.screen.detail.MovieDetailActivity
import asywalul.movie.test.utils.Constant
import com.squareup.picasso.Picasso

class MovieSliderAdapter(val context: Context,private val result: List<Popular>) : PagerAdapter() {

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return result.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val myImageLayout = LayoutInflater.from(view.context).inflate(R.layout.item_slider, view, false)
        val sliderImage = myImageLayout.findViewById<ImageView>(R.id.ivSlider)
        val tvTitle = myImageLayout.findViewById<TextView>(R.id.tvTitle)
        val popular: Popular = result[position]
        tvTitle.text = popular.title

        Picasso.get().load(Constant.API.HOST_IMAGE+popular.posterPath).into(sliderImage)

        myImageLayout.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("type",2)
            intent.putExtra("movie", popular)
            context.startActivity(intent)
        }
        view.addView(myImageLayout, 0)

        return myImageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

}
