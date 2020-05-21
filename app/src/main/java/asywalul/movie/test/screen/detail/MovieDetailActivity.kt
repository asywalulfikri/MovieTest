package asywalul.movie.test.screen.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import asywalul.movie.test.R
import asywalul.movie.test.data.local.entity.Popular
import asywalul.movie.test.data.local.entity.UpComing
import kotlinx.android.synthetic.main.activity_detail_movie.*


class MovieDetailActivity : AppCompatActivity(){

    private var upComing: UpComing? =null
    var popular :Popular? =null
    var type : Int? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        type = intent.getIntExtra("type",0)

        Log.d("typenya",type.toString()+"---")

        if(type==1){
            upComing = intent.getSerializableExtra("movie") as UpComing?
        }else{
            popular = intent.getSerializableExtra("movie") as Popular?
        }

        setupViewPager()
        tab_layout.setupWithViewPager(viewPager)


    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val bundle = Bundle()
        if(type==1){
            bundle.putSerializable("movie", upComing)
            bundle.putSerializable("type",type)
        }else{
            bundle.putSerializable("movie", popular)
            bundle.putSerializable("type",type)
        }

        val information = MovieInformationFragment()
        val reviews = MovieReviewsFragment()

        information.arguments = bundle
        reviews.arguments = bundle
        adapter.addFrag(information, "Information")
        adapter.addFrag(reviews, "Reviews")
        viewPager.adapter = adapter
    }


    class ViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

}