package asywalul.movie.test.screen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asywalul.movie.test.R
import asywalul.movie.test.data.local.entity.Discover
import asywalul.movie.test.utils.Constant
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList


class DiscoverAdapter : RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    private var resultList: ArrayList<Discover> = ArrayList()
    private lateinit var mOnClickListener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view, mOnClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: Discover = resultList[position]
        holder.tvTitle.text = result.title
        Picasso.get()
            .load(Constant.API.HOST_IMAGE+result.posterPath)
            .into(holder.ivMovie, object : Callback {
                override fun onSuccess() {
                    holder.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception) {
                    holder.progressBar.visibility = View.VISIBLE
                }
            })

    }

    fun setItems(resultList: List<Discover>, onClickListener : OnClickListener) {
        this.resultList = resultList as ArrayList<Discover>
        this.mOnClickListener = onClickListener
        notifyDataSetChanged()
    }

    fun addItem(list: List<Discover?>) {
        for (i in list.indices) {
            resultList.add(list[i]!!)
        }
        notifyDataSetChanged()
    }

    fun getList() : ArrayList<Discover>{
        return this.resultList
    }


    override fun getItemCount(): Int {
        return resultList.size
    }

    inner class ViewHolder(view: View, onClickListener: OnClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var tvTitle: TextView = view.findViewById(R.id.tvTitle)
        var ivMovie: ImageView = view.findViewById(R.id.ivMovie)
        var progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        private var mOnClickListener: OnClickListener = onClickListener

        override fun onClick(view: View) {
            mOnClickListener.onClick(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

}