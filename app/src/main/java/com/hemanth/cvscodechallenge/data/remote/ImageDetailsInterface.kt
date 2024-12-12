package com.hemanth.cvscodechallenge.data.remote

import com.hemanth.cvscodechallenge.data.model.ImageItemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageDetailsInterface {
    @GET(ImageDetails.END_POINTS)
    suspend fun getDetails(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("tags") tags: String
    ): ImageItemModel
}