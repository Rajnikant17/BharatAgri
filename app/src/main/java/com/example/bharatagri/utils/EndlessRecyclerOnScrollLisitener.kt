package com.example.bharatagri.utils

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class EndlessRecyclerOnScrollLisitener(layoutManager: RecyclerView.LayoutManager?) : RecyclerView.OnScrollListener() {
    val linearLayoutManager:LinearLayoutManager= layoutManager as LinearLayoutManager
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount: Int = linearLayoutManager.getChildCount()
        val totalItemCount: Int = linearLayoutManager.getItemCount()
        val firstVisibleItemPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE && recyclerView.canScrollVertically(-1)) {
                onScrolledToEnd()
            }
    }
     abstract fun onScrolledToEnd()
}