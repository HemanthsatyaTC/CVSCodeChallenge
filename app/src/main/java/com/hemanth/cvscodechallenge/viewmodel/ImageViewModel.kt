package com.hemanth.cvscodechallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.cvscodechallenge.data.model.ImageItemModel
import com.hemanth.cvscodechallenge.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel()  {

    private val _images = MutableLiveData<ImageItemModel>()
    val images: MutableLiveData<ImageItemModel> = _images

    init {
        fetchImages(tags = "porcupine")
    }

    fun fetchImages(tags: String) {
        viewModelScope.launch {
            _images.value = repository.getDetails(tags)
        }

    }

}