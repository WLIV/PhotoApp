package com.example.photoapp.repository

import androidx.lifecycle.MutableLiveData
import kotlin.random.Random
import kotlin.random.nextInt

class ImageLinkStorage {
    private val imagesUrl = ArrayList<String>()
    private val imagesUrlReturn = ArrayList<String>()
    private val randomAmount = Random
    private var randomAmountInt : Int = 0

    companion object{
        private var instance = ImageLinkStorage()
        public fun getInstance() : ImageLinkStorage = instance
    }


    public fun getImageUrls() : MutableLiveData<ArrayList<String>>{
        setUrls()
        val data = MutableLiveData<ArrayList<String>>()
        randomAmountInt = randomAmount.nextInt(3..15)
        for (i in randomAmountInt downTo 0 step 1){
            imagesUrlReturn.add(imagesUrl[i])
        }
      data.value = imagesUrlReturn
        return data
    }

    private fun setUrls(){
        imagesUrl.add("https://www.cnet.com/a/img/3vwkGb5WITg4dttXkrXYps_kyFg=/1240x775/2020/09/30/3bbaa877-fd32-45dc-84c2-2c685adef434/2020-ford-mustang-shelby-gt350-heritage-edition-3.jpg")
        imagesUrl.add("https://cdn.motor1.com/images/mgl/J7EjQ/s1/electric-ford-mustang-by-charge-cars.jpg")
        imagesUrl.add("https://www.tuningblog.eu/wp-content/uploads/2020/04/RTR-Vehicles-2020-Ford-Mustang-GT-Spec-5-Tuning-Widebody-7.jpg")
        imagesUrl.add("https://www.chevrolet.com/content/dam/chevrolet/na/us/english/index/vehicles/2022/performance/camaro/02-images/mov/2022-camaro-masthead-01.jpg?imwidth=960")
        imagesUrl.add("https://img12.img-bcg.eu/auto24/560/512/114394512.jpg")
        imagesUrl.add("https://www.erclassics.com/media/catalog/product/cache/2/thumbnail/1920x/17f82f742ffe127f42dca9de82fb58b1/c/h/chevrolet-corvette-1978-c7879-040.jpg")
        imagesUrl.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2020-chevrolet-corvette-lead2-1571234772.jpg")
        imagesUrl.add("https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Salon_de_l%27auto_de_Gen%C3%A8ve_2014_-_20140305_-_Chevrolet_Corvette_Stingray_Z06.jpg/1200px-Salon_de_l%27auto_de_Gen%C3%A8ve_2014_-_20140305_-_Chevrolet_Corvette_Stingray_Z06.jpg")
        imagesUrl.add("https://s-media-cache-ak0.pinimg.com/564x/89/e2/ae/89e2ae5e590ea08874f64accb676b795.jpg")
        imagesUrl.add("https://thumbs.dreamstime.com/b/san-marino-ott-old-racing-car-rally-legend-famous-historical-race-volkswagen-golf-gti-v-merino-112340307.jpg")
        imagesUrl.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-dodge-challenger-mmp-1-1599685173.jpg")
        imagesUrl.add("https://uscar.ee/wp-content/uploads/2019/11/Dodge-Challenger-4.jpg")
        imagesUrl.add("https://barrettjacksoncdn.azureedge.net/staging/carlist/items/Fullsize/Cars/237247/237247_Front_3-4_Web.jpg")
        imagesUrl.add("https://www.auto-data.net/images/f32/Dodge-Challenger.jpg")
        imagesUrl.add("https://bringatrailer.com/wp-content/uploads/2020/08/1970_dodge_challenger_1601427098a6c81fDSC_4042.jpg?fit=940%2C627")
    }

}