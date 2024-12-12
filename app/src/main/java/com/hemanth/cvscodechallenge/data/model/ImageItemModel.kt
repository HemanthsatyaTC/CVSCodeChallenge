package com.hemanth.cvscodechallenge.data.model


import com.google.gson.annotations.SerializedName

data class ImageItemModel(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("generator")
    val generator: String? = "",
    @SerializedName("items")
    val items: List<ItemModel?>? = listOf(),
    @SerializedName("link")
    val link: String? = "",
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("title")
    val title: String? = ""
)