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

class ImageViewAdapter(private var mContext: Context, private var imageUrls: ArrayList<String>) : PagerAdapter(){

    override fun getCount() : Int{
        return imageUrls.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater : LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView : View = inflater.inflate(R.layout.pager, container, false)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        var url = imageUrls.get(position)
        Glide.with(mContext).load(url).into(imageView)
        container.addView(itemView, 0)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    fun setImages(imageUrls : ArrayList<String>){
        this.imageUrls = imageUrls
        notifyDataSetChanged()
    }

}