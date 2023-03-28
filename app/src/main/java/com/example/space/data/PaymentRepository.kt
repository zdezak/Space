package com.example.space.data

import com.example.space.data.Payment

interface PaymentRepository {
    fun getPayment(): List<Payment>
}