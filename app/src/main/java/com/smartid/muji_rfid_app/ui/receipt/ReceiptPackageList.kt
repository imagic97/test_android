package com.smartid.muji_rfid_app.ui.receipt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R

class ReceiptPackageList(var view: RecyclerView, var list: ArrayList<State>) {

    init {
        val statusAdapter = ViewAdapter(list)
        val ll = LinearLayoutManager(view.context)
        ll.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = ll
        view.adapter = statusAdapter
    }

    class State(
        var id: String,
        var size: String,
        var isActive: Boolean,
        var isReceipt: Boolean?,
    )

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.receipt_package_id)
        val sizeView: TextView = view.findViewById(R.id.receipt_package_size)
        val isActiveView: ImageView = view.findViewById(R.id.receipt_package_checkbox)
    }

    inner class ViewAdapter(var list: ArrayList<State>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.receipt_package_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.idView.text = item.id
            holder.sizeView.text = item.size
            holder.isActiveView.isActivated = item.isActive
            holder.isActiveView.setOnClickListener {
                item.isActive = !item.isActive
                it.isActivated = item.isActive
            }
            when (item.isReceipt) {
                // 已收到箱
                true -> {
                    holder.view.isActivated = true
                    holder.view.isEnabled = false
                }
                // 未收到箱
                false -> {
                    holder.view.isActivated = false
                    holder.view.isEnabled = true
                }
                // 未确认
                else -> {
                    holder.view.isActivated = false
                    holder.view.isEnabled = false
                }
            }
        }

        override fun getItemCount(): Int = list.size
    }
}