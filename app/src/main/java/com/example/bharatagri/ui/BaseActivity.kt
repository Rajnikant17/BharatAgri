package com.example.bharatagri.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.bharatagri.R
import com.example.bharatagri.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {
    var binding: ActivityBaseBinding? = null
    @get:LayoutRes

    abstract val layoutResourceId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(this),
                R.layout.activity_base,
                null,
                false
            )
        setContentView(binding?.root)
        layoutInflater.inflate(layoutResourceId, binding?.container)
    }

    fun showLoading() {
        binding?.pbProgress?.let {
            it.visibility = View.VISIBLE
        }
    }

    fun hideLoading() {
        binding?.pbProgress?.let {
            if(it.isVisible)
            it.visibility = View.GONE
        }
    }
}