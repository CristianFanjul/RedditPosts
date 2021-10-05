package com.cmf.redditposts.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Select a post to read the details."
    }
    val text: LiveData<String> = _text
}