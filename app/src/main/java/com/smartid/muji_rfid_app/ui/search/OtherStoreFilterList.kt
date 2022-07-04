package com.smartid.muji_rfid_app.ui.search

import android.graphics.Color
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.Application
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.RecyclerViewItemDecoration
import com.smartid.muji_rfid_app.utils.Utils

class OtherStoreFilterList(var view: RecyclerView, var list: ArrayList<String>) {

    init {
        val linearLayoutManager = LinearLayoutManager(view.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        view.layoutManager = linearLayoutManager
        view.adapter = Adapter(list)
        view.addItemDecoration(RecyclerViewItemDecoration(LinearLayoutManager.HORIZONTAL, Application.getContext().resources.getDimension(R.dimen.dp4).toInt()))
    }

    private inner class ViewHolder(var view: Button) : RecyclerView.ViewHolder(view)

    private inner class Adapter(var specs: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val button = Button(view.context)

            val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val padding1 = Utils.dip2px(view.context, 12f).toInt()
            val padding2 = Utils.dip2px(view.context, 5f).toInt()
            button.layoutParams = layoutParams
            button.setPadding(padding1, padding2, padding1, padding2)
            button.setBackgroundResource(R.drawable.shape_search_btn_black)
            button.setTextColor(Color.WHITE)


            return ViewHolder(button)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item: String = specs[position]
            holder.view.text = item
        }

        override fun getItemCount(): Int {
            return specs.size
        }
    }
}