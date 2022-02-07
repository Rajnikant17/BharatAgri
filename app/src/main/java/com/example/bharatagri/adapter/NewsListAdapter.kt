package com.example.bharatagri.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bharatagri.adapter.viewholder.LoaderViewHolder
import com.example.bharatagri.adapter.viewholder.NewsArticleViewHolder
import com.example.bharatagri.databinding.NewsListAdapterBinding
import com.example.bharatagri.databinding.ProgressLoadingBarBinding
import com.example.bharatagri.repository.NewsArticle
import com.example.bharatagri.utils.RecycleViewItemViewClickListener
import javax.inject.Inject

const val VIEW_NEWS_ARTICLE = 1
const val VIEW_LOADER = 2
class MovieListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var attachedToRecyclerView = false
    private var itemClickListener: RecycleViewItemViewClickListener<NewsArticle>? = null
    private var items: MutableList<NewsArticle?> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_NEWS_ARTICLE -> NewsArticleViewHolder(NewsListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false),parent.context)
            else -> LoaderViewHolder(ProgressLoadingBarBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items.isNotEmpty()) {
            when (getItemViewType(position)) {
                VIEW_NEWS_ARTICLE -> {
                    (holder as NewsArticleViewHolder).bind(items[position] as NewsArticle)
                    holder.itemRowBinding.itemClickListener = itemClickListener
                }
                else ->{
                    holder as LoaderViewHolder
                }
            }
        }
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        attachedToRecyclerView = true
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is NewsArticle -> VIEW_NEWS_ARTICLE
            else -> VIEW_LOADER
        }
    }

    fun setItems(moreItems: List<NewsArticle?>) {
        items.addAll(moreItems)
        if (attachedToRecyclerView) {
            notifyItemRangeInserted((items.size-moreItems.size),moreItems.count())
        }
    }

    fun getItems(): MutableList<NewsArticle?> {
        return items
    }

    fun setOnClickListener(itemClickListener: RecycleViewItemViewClickListener<NewsArticle>?) {
        itemClickListener?.let {
            this.itemClickListener = it
        }
    }
}