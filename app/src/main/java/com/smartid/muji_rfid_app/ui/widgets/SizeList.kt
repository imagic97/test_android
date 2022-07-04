package com.smartid.muji_rfid_app.ui.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.RecyclerViewItemDecoration
import com.smartid.muji_rfid_app.utils.Utils

class SizeList(val view: RecyclerView, val list: ArrayList<SizeState>) {

    init {
        val layoutManager = LinearLayoutManager(view.context)
        val divider = Utils.dip2px(view.context, 2.5f).toInt()

        if (!view.isActivated) {
            view.addItemDecoration(RecyclerViewItemDecoration(LinearLayoutManager.HORIZONTAL, divider))
        }
        view.isActivated = true

        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        view.layoutManager = layoutManager
        view.adapter = SizeAdapter(list)
    }


    inner class SizeAdapter(val list: ArrayList<SizeState>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.widgets_sizes_list_item, parent, false)
            return SizeHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val item = list[position]
            // TODO 点击出现弹窗
            (holder as SizeHolder).nameView.text = item.name
            holder.countView.text = item.count
        }

        override fun getItemCount(): Int = list.size
    }


    inner class SizeHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameView: TextView = view.findViewById(R.id.replenish_size_name)
        var countView: TextView = view.findViewById(R.id.replenish_size_count)
    }

    class SizeState(val name: String, val count: String)
}