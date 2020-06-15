package asywalul.movie.test.screen.detail.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import asywalul.movie.test.R
import asywalul.movie.test.data.local.entity.Videos
import asywalul.movie.test.utils.Constant
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.*


class VideoAdapter : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private var resultList: List<Videos> = ArrayList()
    private var context : Context? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: Videos = resultList[position]
        holder.tvTitle.text = result.name
        Picasso.get()
            .load("http://img.youtube.com/vi/"+result.key+"/0.jpg")
            .into(holder.ivMovie, object : Callback {
                override fun onSuccess() {

                }

                override fun onError(e: Exception) {

                }
            })

        holder.ivPlay.setOnClickListener {
            val intent = YouTubeStandalonePlayer.createVideoIntent(context as Activity?,Constant.KEY.youtubeKey, result.key)
            context!!.startActivity(intent)
        }
    }

    fun setItems(resultList: List<Videos>,context: FragmentActivity) {
        this.context = context
        this.resultList = resultList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return resultList.size
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.tvTitle)
        var ivMovie: ImageView = view.findViewById(R.id.ivMovie)
        var ivPlay : ImageView = view.findViewById(R.id.ivPlay)

    }

}