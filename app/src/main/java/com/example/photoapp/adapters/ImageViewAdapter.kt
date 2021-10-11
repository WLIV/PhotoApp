package com.example.photoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.photoapp.R

//todo список с картинками в конструкторе не нужен. В конструкторе лучше всегда передавать val
class ImageViewAdapter(private var mContext: Context, private var imageUrls: List<String>) : PagerAdapter(){

    override fun getCount() : Int{
        return imageUrls.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // todo LayoutInflater.from(mContext), так короче
        //val inflater : LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //val itemView : View = inflater.inflate(R.layout.pager, container, false)
        val imageView = ImageView(mContext)
        var url = imageUrls.get(position)
        Glide.with(mContext).load(url).into(imageView)
        container.addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //todo не касти тут к чему либо кроме View
        //container.removeView(`object` as LinearLayout)
        container.removeView(`object` as View)
    }

    fun setImages(imageUrls : List<String>){
        this.imageUrls = imageUrls
        notifyDataSetChanged()
    }

}