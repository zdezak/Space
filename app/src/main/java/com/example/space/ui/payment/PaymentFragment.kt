package com.example.space.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.space.databinding.FragmentPaymentBinding
import com.example.space.ui.news.NewsAdapter

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val paymentViewModel =
            ViewModelProvider(this)[PaymentViewModel::class.java]

        _binding = FragmentPaymentBinding.inflate(inflater, container, false)

        val manager = LinearLayoutManager(this.context)
        val adapter = PaymentAdapter()

        lifecycleScope.launchWhenStarted {
            paymentViewModel.payment.collect {payment->
                adapter.data = payment
            }
        }

        binding.listPayment.layoutManager = manager
        binding.listPayment.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}