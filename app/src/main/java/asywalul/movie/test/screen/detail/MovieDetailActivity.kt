package asywalul.movie.test.screen.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import asywalul.movie.test.R
import asywalul.movie.test.data.local.entity.UpComing
import kotlinx.android.synthetic.main.activity_detail_movie.*


class MovieDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val people: UpComing = intent.getParcelableExtra("movie")
        Toast.makeText(this,people.originalTitle,Toast.LENGTH_SHORT).show()

        setupViewPager()


    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(MovieInformationFragment(), "Comment")
        adapter.addFrag(MovieReviewsFragment(), "Review")
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