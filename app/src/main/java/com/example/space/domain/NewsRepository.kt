package com.example.space.domain

import com.example.space.data.News

interface NewsRepository {
    fun getNews(): List<News>
}