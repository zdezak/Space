package com.example.space.data

import com.github.javafaker.Faker

class NewsService {
    private var news = mutableListOf<News>() // Все пользователи

    init {
        val faker = Faker.instance() // Переменная для создания случайных данных

        news = (1..50).map {
            News(
                label = faker.book().title(),
                date = faker.date().toString(),
                text = faker.book().publisher()
            )
        }.toMutableList()
    }

    fun getNews(): List<News> {
        return news
    }
}