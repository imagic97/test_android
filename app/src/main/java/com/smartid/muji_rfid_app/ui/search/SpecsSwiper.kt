package com.smartid.muji_rfid_app.ui.search

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.smartid.muji_rfid_app.Application
import com.smartid.muji_rfid_app.R
import kotlin.math.max
import kotlin.math.min


class SpecsSwiper(var context: Context, var view: RecyclerView, var list: ArrayList<String>) {

    private var lastItemPosition: Int = 0
    private var firstItemPosition: Int = 4

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.search_specs_item)
    }

    inner class ViewAdapter(var dataList: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.search_swiper_specs_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var item = dataList[position]
            // TODO
            // holder.imageView.setImageResource()
        }

        override fun getItemCount(): Int = dataList.size


    }

    inner class ItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.left = Application.getContext().resources.getDimension(R.dimen.dp4).toInt()
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = 0
            }
        }
    }

    init {
        val adapter = ViewAdapter(list)

        var linearManager = LinearLayoutManager(context)
        linearManager.orientation = LinearLayoutManager.HORIZONTAL

        view.layoutManager = linearManager
        view.addItemDecoration(ItemDecoration())
        view.adapter = adapter

        LinearSnapHelper().attachToRecyclerView(view)

        view.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager) {
                    //获取最后一个可见view的位置
                    lastItemPosition = linearManager.findLastVisibleItemPosition()
                    //获取第一个可见view的位置
                    firstItemPosition = linearManager.findFirstVisibleItemPosition()
                    Log.i("-->", "$firstItemPosition, $lastItemPosition")
                }
            }
        })
    }

    fun next() {
        var index = (view.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        val i = min(++index, list.size)
        view.smoothScrollToPosition(i)
    }

    fun prev() {
        var index = (view.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val i = max(--index, 0)
        view.smoothScrollToPosition(i)
    }
}