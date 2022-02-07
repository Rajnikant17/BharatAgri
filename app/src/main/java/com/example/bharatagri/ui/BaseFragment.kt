package com.example.bharatagri.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bharatagri.R
import com.example.bharatagri.utils.DataState
import com.example.bharatagri.utils.hideLoading
import com.example.bharatagri.utils.showLoading

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    @get:LayoutRes
    abstract val fragmentLayoutResId: Int
    protected var binding: VDB ?=null
    @Nullable
    protected  var errorView: View?=null
    @Nullable
    protected  var btRetry: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, fragmentLayoutResId, container, false/*, bindingComponent*/)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorView=view.findViewById(R.id.rlError)
        btRetry=view.findViewById(R.id.bt_error)
        btRetry?.setOnClickListener { retry() }
        binding?.let {
            it.lifecycleOwner = this
        }
    }
    abstract fun loadData()

    open val observer = Observer<DataState<*>> {
        when (it) {
            is DataState.Loading -> {
                showLoading()
            }
            is DataState.Error<*> -> {
                // Different error codes has been handled in BaseDataSource class .
                hideLoading()
                showRetryErrorView()
            }
            is DataState.Success<*> -> {
                hideLoading()
                receivedResponse(it.data)
            }
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }
    fun showRetryErrorView(){
        errorView?.visibility=View.VISIBLE
    }
    fun hideRetryErrorView(){
        errorView?.visibility=View.GONE
    }
    fun retry(){
      loadData()
    }
    abstract fun receivedResponse(item: Any?)
}