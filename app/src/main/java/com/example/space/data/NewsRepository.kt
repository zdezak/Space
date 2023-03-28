package com.example.space.data

import com.example.space.data.News

interface NewsRepository {
    fun getNews(): List<News>
}