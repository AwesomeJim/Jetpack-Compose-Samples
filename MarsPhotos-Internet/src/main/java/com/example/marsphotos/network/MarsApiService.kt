package com.example.marsphotos.network

import com.example.marsphotos.model.Amphibian
import retrofit2.http.GET




interface MarsApiService {
    @GET("photos")
  suspend fun getPhotos():  List<MarsPhoto>

    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}



