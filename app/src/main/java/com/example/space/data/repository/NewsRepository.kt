package com.example.space.data.repository

import com.example.space.data.model.News

interface NewsRepository {
    suspend fun getNews(): List<News>
    suspend fun setNews(newsList: List<News>)
}