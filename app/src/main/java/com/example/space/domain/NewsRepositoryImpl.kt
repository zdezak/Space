package com.example.space.domain

import com.example.space.data.News
import com.github.javafaker.Faker

class NewsRepositoryImpl : NewsRepository {
    override fun getNews(): List<News> {
        val news: MutableList<News> // Все пользователи

        val faker = Faker.instance() // Переменная для создания случайных данных

        news = (1..50).map {
            News(
                label = faker.book().title(),
                date = faker.date().toString(),
                text = faker.book().publisher()
            )
        }.toMutableList()
        return news
    }
}