package com.example.photoapp.repository

import com.example.photoapp.interfaces.ImageRepository

class ImageLinkStorage: ImageRepository {

    private val urls = listOf(
        "https://www.cnet.com/a/img/3vwkGb5WITg4dttXkrXYps_kyFg=/1240x775/2020/09/30/3bbaa877-fd32-45dc-84c2-2c685adef434/2020-ford-mustang-shelby-gt350-heritage-edition-3.jpg",
        "https://cdn.motor1.com/images/mgl/J7EjQ/s1/electric-ford-mustang-by-charge-cars.jpg",
        "https://www.tuningblog.eu/wp-content/uploads/2020/04/RTR-Vehicles-2020-Ford-Mustang-GT-Spec-5-Tuning-Widebody-7.jpg",
        "https://www.chevrolet.com/content/dam/chevrolet/na/us/english/index/vehicles/2022/performance/camaro/02-images/mov/2022-camaro-masthead-01.jpg?imwidth=960",
        "https://img12.img-bcg.eu/auto24/560/512/114394512.jpg",
        "https://www.erclassics.com/media/catalog/product/cache/2/thumbnail/1920x/17f82f742ffe127f42dca9de82fb58b1/c/h/chevrolet-corvette-1978-c7879-040.jpg",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2020-chevrolet-corvette-lead2-1571234772.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Salon_de_l%27auto_de_Gen%C3%A8ve_2014_-_20140305_-_Chevrolet_Corvette_Stingray_Z06.jpg/1200px-Salon_de_l%27auto_de_Gen%C3%A8ve_2014_-_20140305_-_Chevrolet_Corvette_Stingray_Z06.jpg",
        "https://s-media-cache-ak0.pinimg.com/564x/89/e2/ae/89e2ae5e590ea08874f64accb676b795.jpg",
        "https://thumbs.dreamstime.com/b/san-marino-ott-old-racing-car-rally-legend-famous-historical-race-volkswagen-golf-gti-v-merino-112340307.jpg",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-dodge-challenger-mmp-1-1599685173.jpg",
        "https://uscar.ee/wp-content/uploads/2019/11/Dodge-Challenger-4.jpg",
        "https://barrettjacksoncdn.azureedge.net/staging/carlist/items/Fullsize/Cars/237247/237247_Front_3-4_Web.jpg",
        "https://www.auto-data.net/images/f32/Dodge-Challenger.jpg",
        "https://bringatrailer.com/wp-content/uploads/2020/08/1970_dodge_challenger_1601427098a6c81fDSC_4042.jpg?fit=940%2C627"
    )

    override fun getImages(amount: Int): List<String> {
        //берем первые amount элементов
        return urls.take(amount)
    }

}