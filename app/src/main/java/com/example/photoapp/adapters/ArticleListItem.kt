package com.example.photoapp.adapters

import com.example.photoapp.R

class ArticleListItem(var title : String?, var description : String?, var urlToImage : String?) {
    init {
        if (title == null){
            title = R.string.noTitle.toString()
        }
        if (description == null){
            description = R.string.noDescription.toString()
        }
        if (urlToImage == null){
            urlToImage = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png"  //картинка с надписью что нет доступной картинки, на случай если у статьи нет картинки
        }
    }
}