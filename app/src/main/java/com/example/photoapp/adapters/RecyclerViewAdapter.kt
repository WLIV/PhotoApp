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
import com.example.photoapp.retrofitadapter.Article


class RecyclerViewAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private var articleList: List<Article>? = mutableListOf()

    //todo для адаптера нужно всегда создавать отдельный класс.
    // У тебя по сути сейчас используется только 3 вещи: заголовок, описание и картинка.
    // Создай каокй-нибудь ArticleListItem и object, который будет "переводить" Article в ArticleListItem
    fun setArticleList(articleList: List<Article>?) {
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
        //todo старайся никогда не делать nullable списки. Если только на 100% не уверен, что это нужно
        //он может же быть просто пустым
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

        fun bind(news: Article) {
            title.text = news.title
            description.text = news.description
            Glide.with(mContext).load(news.urlToImage).into(imageView)

        }

    }
}