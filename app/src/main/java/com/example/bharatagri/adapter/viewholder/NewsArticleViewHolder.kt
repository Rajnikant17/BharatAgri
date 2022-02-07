package com.example.bharatagri.adapter.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bharatagri.R
import com.example.bharatagri.databinding.NewsListAdapterBinding
import com.example.bharatagri.repository.NewsArticle
import com.example.bharatagri.utils.DateFormatter

class NewsArticleViewHolder (val itemRowBinding: NewsListAdapterBinding,val context: Context) : RecyclerView.ViewHolder(itemRowBinding.root) {
    fun bind(item: NewsArticle) {
        itemRowBinding.newsArticleList=item
        itemRowBinding.newsSource=item.source
        itemRowBinding.tvPublishedDate.text=DateFormatter.getDate(item.publishedAt)
                Glide.with(context).load(item.urlToImage).error(R.color.purple_light).into(itemRowBinding.ivNews)
    }
}