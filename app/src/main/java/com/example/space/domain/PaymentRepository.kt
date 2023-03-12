package com.example.space.domain

import com.example.space.data.Payment

interface PaymentRepository {
    fun getPayment(): List<Payment>
}