package asywalul.movie.test.screen.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import asywalul.movie.test.R
import asywalul.movie.test.data.local.entity.Reviews
import java.util.*


class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    private var resultList: List<Reviews> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: Reviews = resultList[position]
        holder.tvAuthor.text = result.author
        holder.tvContent.text = result.content


    }

    fun setItems(resultList: List<Reviews>) {
        this.resultList = resultList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return resultList.size
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var tvAuthor: TextView = view.findViewById(R.id.tvAuthor)
        var tvContent: TextView = view.findViewById(R.id.tvContent)

    }

}