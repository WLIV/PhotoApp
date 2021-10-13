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
import com.kwabenaberko.newsapilib.models.Article


class RecyclerViewAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private lateinit var articleList: List<Article>

    fun setArticleList(articleList: List<Article>) {
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
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int {
    return articleList.size
    }

    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val title : TextView = view.findViewById(R.id.article_title)
        private val description : TextView = view.findViewById(R.id.article_description)
        private val imageView : ImageView = view.findViewById(R.id.article_image)

        fun bind(article: Article) {
            title.text = article.title
            description.text = article.description
            Glide.with(mContext).load(article.urlToImage).into(imageView)

        }

    }
}