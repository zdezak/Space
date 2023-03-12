package com.example.space.ui.payment

import androidx.lifecycle.ViewModel
import com.example.space.data.Payment
import com.example.space.domain.PaymentRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaymentViewModel : ViewModel() {
    private val repository = PaymentRepositoryImpl()
    private val _payment = MutableStateFlow(repository.getPayment())
    val payment: StateFlow<List<Payment>> = _payment.asStateFlow()
}