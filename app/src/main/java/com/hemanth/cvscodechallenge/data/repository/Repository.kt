package com.hemanth.cvscodechallenge.data.repository

import com.hemanth.cvscodechallenge.data.model.ImageItemModel

interface Repository {

    suspend fun getDetails(tags: String): ImageItemModel
}