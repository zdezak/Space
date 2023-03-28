package com.example.space.ui.news

import androidx.lifecycle.ViewModel
import com.example.space.data.News
import com.example.space.data.NewsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel : ViewModel() {
    private val repository = NewsRepositoryImpl()
    private val _news = MutableStateFlow(repository.getNews())
    val news: StateFlow<List<News>> = _news.asStateFlow()
}