package com.example.bharatagri.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.bharatagri.R
import androidx.lifecycle.Observer
import com.example.bharatagri.utils.DataState
import com.example.bharatagri.utils.EndlessRecyclerOnScrollLisitener
import com.example.bharatagri.utils.hideLoading
import com.example.bharatagri.utils.showLoading
import javax.inject.Inject

abstract class ListItemFragment<A : RecyclerView.Adapter<RecyclerView.ViewHolder> , VDB : ViewDataBinding>:BaseFragment<VDB>() {

    @Inject
    lateinit var adapter: A
    var recyclerView:RecyclerView?=null
    var paginationEnabled=true
    var currentPage=1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.rv_parent)
        setAdapter()
        recyclerView?.addOnScrollListener(object :
            EndlessRecyclerOnScrollLisitener(recyclerView?.layoutManager) {
            override fun onScrolledToEnd() {
                if(paginationEnabled && currentPage>1) {
                    loadPage(currentPage)
                    paginationEnabled = false
                }
            }
        })
    }
     override val observer = Observer<DataState<*>> {
        when (it) {
            is DataState.Loading -> {
                hideRetryErrorView()
                if(adapter.itemCount<1) showLoading()
            }
            is DataState.Error<*> -> {
                // Different error codes has been handled in BaseDataSource class .
                hideLoading()
                removeLoadingItem()
                paginationEnabled=true
                if(adapter.itemCount>0)
                    it.errorMsg?.let { it1 -> showToast(it1) }
                else {
                    showRetryErrorView()
                }
            }
            is DataState.Success<*> -> {
               hideLoading()
                currentPage++
                removeLoadingItem()
                receivedResponse(it.data)
            }
        }
    }

    abstract fun removeLoadingItem()
    abstract fun loadPage(currentPage:Int)

    fun setAdapter() {
        recyclerView?.adapter = adapter
    }
    fun <T> addLoadingItem(loadingViewPosition:Int, list:MutableList<T?>){
        list.add(loadingViewPosition,null)
        adapter.notifyItemInserted(loadingViewPosition)
    }

    fun <T> removeLoadingItem(loadingViewPosition:Int,list:MutableList<T?>){
       if(loadingViewPosition > 0 && loadingViewPosition < list.size) {
            list.removeAt(loadingViewPosition)
            adapter.notifyItemRemoved(loadingViewPosition)
        }
    }

}