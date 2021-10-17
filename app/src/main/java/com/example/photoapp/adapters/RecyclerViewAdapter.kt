package com.example.photoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photoapp.R


class RecyclerViewAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private var articleList: List<com.example.photoapp.repository.Article>? = mutableListOf()

    fun setArticleList(articleList: List<com.example.photoapp.repository.Article>?) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.text_row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.bind(articleList!![position])
    }

    override fun getItemCount(): Int {
        if (articleList == null){
            return 0
        }
    return articleList!!.size
    }

    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val title : TextView = view.findViewById(R.id.article_title)
        private val description : TextView = view.findViewById(R.id.article_description)
        private val imageView : ImageView = view.findViewById(R.id.article_image)

        fun bind(news: com.example.photoapp.repository.Article) {
            title.text = news.title
            description.text = news.description
            Glide.with(mContext).load(news.urlToImage).into(imageView)

        }

    }
}