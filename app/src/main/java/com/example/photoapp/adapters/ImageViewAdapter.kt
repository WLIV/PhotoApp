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


class ImageViewAdapter(private val mContext: Context) : PagerAdapter(){
    private var imageUrls : List<String> = mutableListOf()

    override fun getCount() : Int{
        return imageUrls.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val imageView = ImageView(mContext)
        var url = imageUrls.get(position)
        Glide.with(mContext).load(url).into(imageView)
        container.addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun setImages(imageUrls : List<String>){
        this.imageUrls = imageUrls
        notifyDataSetChanged()
    }

}