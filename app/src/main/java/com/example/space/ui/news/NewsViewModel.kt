package com.example.space.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.space.data.model.News
import com.example.space.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepositoryImpl()
    private val _news = MutableStateFlow(emptyList<News>())
    val news: StateFlow<List<News>>
        get() = _news

    init {
        viewModelScope.launch {
            _news.value = repository.getNews()
        }
    }


}