package com.example.space.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.space.data.model.News
import com.example.space.databinding.ItemNewsBinding
import com.example.space.domain.Convert

class NewsAdapter(var data: List<News>) :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback) {
    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder.binding) {
            labelNews.text = data[position].label
            dateNews.text = Convert.convertLongToDate(data[position].date)
            textNews.text = data[position].text
        }
    }

    object NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return  oldItem.id == newItem.id
        }
    }
}