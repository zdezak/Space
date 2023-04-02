package com.example.space.ui.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.space.data.model.Payment
import com.example.space.databinding.ItemPaymentBinding

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    var data: List<Payment> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class PaymentViewHolder(val binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaymentBinding.inflate(inflater, parent, false)

        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = data[position]

        with(holder.binding) {
            nameService.text = payment.nameService
            summa.text = payment.sum
            checkBox.isChecked = payment.isChecked
        }
    }

    override fun getItemCount(): Int = data.size

}