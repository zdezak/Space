package com.example.space.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.space.data.model.News
import com.example.space.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepositoryImpl()
    private val _news = MutableStateFlow(emptyList<News>())
    val news: StateFlow<List<News>>
        get() = _news.asStateFlow()

    private val _newsLiveData = MutableLiveData<List<News>>()
    val newsLiveData: LiveData<List<News>>
        get() = _newsLiveData

    init {
        viewModelScope.launch {
            _news.value = repository.getNews()
            _newsLiveData.value = repository.getNews()
        }
    }


}