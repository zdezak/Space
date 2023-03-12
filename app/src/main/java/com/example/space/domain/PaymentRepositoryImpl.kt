package com.example.space.domain

import com.example.space.data.News
import com.example.space.data.Payment
import com.github.javafaker.Faker

class PaymentRepositoryImpl : PaymentRepository {
    override fun getPayment(): List<Payment> {
        val payment: MutableList<Payment> // Все пользователи

        val faker = Faker.instance() // Переменная для создания случайных данных

        payment = (1..50).map {
            Payment(
                nameService = faker.book().title(),
                sum = faker.book().publisher(),
                isChecked = it%2==0
            )
        }.toMutableList()
        return payment
    }
}