package com.hemanth.cvscodechallenge.data.repository

import com.hemanth.cvscodechallenge.data.model.ImageItemModel
import com.hemanth.cvscodechallenge.data.remote.ImageDetailsInterface
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    val imageDetails: ImageDetailsInterface
): Repository {
    override suspend fun getDetails(tags: String): ImageItemModel {
        return imageDetails.getDetails(tags = tags.toString())
    }
}