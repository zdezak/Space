package com.example.space.data.repository

import com.example.space.data.model.Payment

interface PaymentRepository {
    fun getPayment(): List<Payment>
}