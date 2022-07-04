package com.smartid.muji_rfid_app.ui.search

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.utils.Utils

class SpecsInfo(var view: RecyclerView, var list: ArrayList<State>) {

    init {
        val ll = LinearLayoutManager(view.context)
        ll.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = ll
        view.adapter = Adapter(list)
    }

    class State(var key: String, val value: String)

    private inner class ViewHolder(var view: LinearLayout) : RecyclerView.ViewHolder(view) {
        var keyView: TextView = view.findViewById(R.id.spec_key)
        var valueView: TextView = view.findViewById(R.id.spec_value)
    }

    private inner class Adapter(var list: ArrayList<State>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(view.context).inflate(R.layout.fragment_search_specs_info_item, parent, false)
            return ViewHolder(itemView as LinearLayout)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item: State = list[position]

            if (item.key !== "IMAGE") {
                holder.keyView.text = item.key
                holder.valueView.text = item.value
            } else {
                holder.view.removeAllViews()
                holder.view.gravity = Gravity.CENTER
                val padding = Utils.dip2px(view.context, 10f).toInt()
                holder.view.setPadding(0, padding, 0, padding)

                val imageView = ImageView(view.context)
                val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val imageWidth = Utils.dip2px(view.context, 192f).toInt()
                layoutParams.width = imageWidth
                layoutParams.height = imageWidth
                imageView.layoutParams = layoutParams
                // TODO
                // imageView.setImageResource(R.drawable.ic_search_details)
                imageView.setBackgroundColor(0xFFEEF1F4.toInt())
                holder.view.addView(imageView)
            }

        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
}