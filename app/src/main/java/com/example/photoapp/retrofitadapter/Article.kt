package com.example.photoapp.retrofitadapter

data class Article (
    var source_url : String?,
    var published : String?,
    var published_method_found : String?,
    var published_guess_accuracy : String?,
    var title: String?,
    var text : String?,
    var authors: List<String>?,
    var images : List<String?>?,
    var top_image : String?,
    var movies : List<String?>?,
    var meta_keywords : List<String?>?,
    var tags : List<String?>?,
    var meta_description : String?,
    var meta_lang : String?,
    var title_lang : String?,
    var text_lang : String?,
    var meta_favicon : String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?,
    var source: Source?,
){

}