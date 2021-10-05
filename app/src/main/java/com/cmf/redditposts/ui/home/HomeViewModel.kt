package com.cmf.redditposts.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmf.redditposts.domain.GetArticlesUseCase
import com.cmf.redditposts.domain.Result
import com.cmf.redditposts.model.Article
import com.cmf.redditposts.model.QueryParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articlesUseCase: GetArticlesUseCase
) : ViewModel() {
    private val _items = MutableLiveData<MutableList<Article>>()
    val items: LiveData<MutableList<Article>> = _items

    private val _loadMoreItems = MutableLiveData<MutableList<Article>>()
    val loadMoreItems: LiveData<MutableList<Article>> = _loadMoreItems

    var initialParams = QueryParams()
    var loadMoreParams = QueryParams()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    fun loadInitialArticles() {
        _dataLoading.value = true
        requestArticles(initialParams) {
            _items.postValue(it)
        }
    }

    fun refreshArticles() {
        requestArticles(initialParams) {
            _items.postValue(it)
        }
    }

    fun loadMoreArticles() {
        requestArticles(loadMoreParams) {
            _loadMoreItems.postValue(it)
        }
    }

    private fun requestArticles(params: QueryParams, onSuccess: (MutableList<Article>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse = articlesUseCase.invoke(params)
            if (apiResponse is Result.Success) {
                val data = apiResponse.content.data
                loadMoreParams.after = data?.after ?: loadMoreParams.after
                val articlesList = data?.children?.mapNotNull {
                    it.article
                } ?: emptyList()
                _dataLoading.postValue(false)
                onSuccess(articlesList.toMutableList())
            } else {
                _dataLoading.postValue(false)
                val message = (apiResponse as Result.Error).exception.message
                _snackbarText.postValue(message!!)
            }
        }
    }

    fun onArticleClicked(item: Article) {
        item.read = true
        _items.value?.indexOf(item)?.let {
            _items.value?.get(it)?.read = true
        }
    }

    fun onArticleDismissed(item: Article) {
        _items.value?.remove(item)
    }
}