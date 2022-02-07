package com.example.bharatagri.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.bharatagri.R
import com.example.bharatagri.adapter.MovieListAdapter
import com.example.bharatagri.databinding.FragmentNewsListBinding
import com.example.bharatagri.repository.NewsArticle
import com.example.bharatagri.repository.NewsArticleResponse
import com.example.bharatagri.utils.*
import com.example.bharatagri.viewmodels.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : ListItemFragment<MovieListAdapter, FragmentNewsListBinding>(),
    RecycleViewItemViewClickListener<NewsArticle> {
    override val fragmentLayoutResId: Int
        get() = R.layout.fragment_news_list
    private val newsListViewModel: NewsListViewModel by viewModels()
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        newsListViewModel.movieListLiveData.observe(viewLifecycleOwner, observer)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.news)
        adapter.setOnClickListener(this)
    }

    override fun loadData() {
        newsListViewModel.getNewsList(1)
    }

    override fun receivedResponse(item: Any?) {
        item?.let { response ->
            when (response) {
                is NewsArticleResponse -> {
                    response.articles?.let { it1 ->
                        if (it1.size > 0) {
                            paginationEnabled = true
                            adapter.setItems(it1)
                        }
                    }
                }
                else -> {
                }
            }
        }
    }

    override fun onClick(item: NewsArticle) {
        val bundle = Bundle()
        bundle.putString(NEWS_IMAGE, item.urlToImage)
        bundle.putString(NEWS_TITLE, item.title)
        bundle.putString(DESCRIPTION, item.description)
        bundle.putString(NEWS_CONTENT, item.content)
        bundle.putString(SOURCE_NAME, item.source?.name)
        bundle.putString(PUBLISHED_DATE, item.publishedAt)
        navController.navigate(R.id.action_newsListFragment_to_newsDetailsFragment, bundle)
    }

    override fun loadPage(currentPage: Int) {
        addLoadingItem(adapter.itemCount, adapter.getItems())
        newsListViewModel.getNewsList(currentPage)
    }

    override fun removeLoadingItem() {
        Log.d("xdfgfdxdxkj", (adapter.itemCount - 1).toString())
        removeLoadingItem(adapter.itemCount - 1, adapter.getItems())
    }

}