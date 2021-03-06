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
    private var articleList: List<ArticleListItem> = mutableListOf()

    fun setArticleList(articleList: List<ArticleListItem>) {
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

        fun bind(news: ArticleListItem) {
            if (news.title == null){
                title.text = R.string.noTitle.toString()
            }
            if (news.description == null){
                description.text = R.string.noDescription.toString()
            }
            title.text = news.title
            description.text = news.description
            Glide
                .with(mContext)
                .load(news.urlToImage)
                .placeholder(R.drawable.ic_image_not_found)
                .into(imageView)

        }

    }
}