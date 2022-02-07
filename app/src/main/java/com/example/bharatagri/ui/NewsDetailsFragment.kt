package com.example.bharatagri.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bharatagri.R
import com.example.bharatagri.databinding.FragmentNewsDetailsBinding
import com.example.bharatagri.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding>() {
    override val fragmentLayoutResId: Int
        get() = R.layout.fragment_news_details
    var newsImageUrl: String? = null
    var newsTitle: String? = null
    var newsDescription: String? = null
    var newsContent: String? = null
    var newsSourcename: String? = null
    var newsPublishedDate: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsImageUrl = it.getString(NEWS_IMAGE)
            newsTitle = it.getString(NEWS_TITLE)
            newsSourcename = it.getString(SOURCE_NAME)
            newsPublishedDate = it.getString(PUBLISHED_DATE)
            newsDescription = it.getString(DESCRIPTION)
            newsContent = it.getString(NEWS_CONTENT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.news_detail)
        setDataInViews()
    }
    fun setDataInViews() {
        binding?.ivNews?.let { Glide.with(requireActivity()).load(newsImageUrl).error(R.drawable.placeholder).into(it) }
        binding?.tvTitle?.text=newsTitle
        binding?.tvDescription?.text=newsDescription
        binding?.tvSource?.text=newsSourcename
        binding?.tvPublishedDate?.text=DateFormatter.getDate(newsPublishedDate)
        binding?.tvContent?.text=newsContent
    }
    override fun receivedResponse(item: Any?) {
    }
    override fun loadData() {

    }
}