package asywalul.movie.test.screen.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asywalul.movie.test.R
import asywalul.movie.test.data.local.entity.Genres
import java.util.ArrayList


class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private var resultList: List<Genres> = ArrayList()
    private lateinit var mOnClickListener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view, mOnClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: Genres = resultList[position]
        holder.tvName.text = result.name

    }

    fun setItems(resultList: List<Genres>, onClickListener : OnClickListener) {
        this.resultList = resultList
        this.mOnClickListener = onClickListener
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return resultList.size
    }

    inner class ViewHolder(view: View, onClickListener: OnClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var tvName: TextView = view.findViewById(R.id.tvName)

        private var mOnClickListener: OnClickListener = onClickListener

        override fun onClick(view: View) {
            mOnClickListener.onClickGenre(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface OnClickListener {
        fun onClickGenre(position: Int)
    }

}