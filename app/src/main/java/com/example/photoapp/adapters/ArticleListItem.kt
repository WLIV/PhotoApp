package com.example.photoapp.adapters

import com.example.photoapp.R

//todo это просто модель данных и тут не должно быть никакой логики
//сделай этот класс как data class
//в дата классе желательно всегда все поля делать val
//"Без названия" и "без описания" подставляй в адаптере
//то есть если title == null, в текст вью вместо заголовка пишешь "без названия"
//в случае отсутствия картинки она просто не отображается. Но если хочется, то в глайде есть placeholder
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