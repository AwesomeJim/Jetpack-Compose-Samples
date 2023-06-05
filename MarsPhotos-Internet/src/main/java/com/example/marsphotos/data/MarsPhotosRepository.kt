package com.example.marsphotos.data

import com.example.marsphotos.model.Amphibian
import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>

    /** Retrieves list of amphibians from underlying data source */
    suspend fun getAmphibians(): List<Amphibian>
}



class DefaultMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return marsApiService.getPhotos()
    }

    /** Retrieves list of amphibians from underlying data source */
    override suspend fun getAmphibians(): List<Amphibian> = marsApiService.getAmphibians()
}