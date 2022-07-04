package com.smartid.muji_rfid_app.ui.receipt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R

class ReceiptCommodityList(var view: RecyclerView, var list: ArrayList<State>) {

    init {
        val adapter = ViewAdapter(list)
        val ll = LinearLayoutManager(view.context)
        ll.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = ll
        view.adapter = adapter
    }

    class State(
        var id: String,
        var expectCount: String,
        var readCount: String,
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.commodity_id)
        val expectCountView: TextView = view.findViewById(R.id.commodity_expect_count)
        val readCountView: TextView = view.findViewById(R.id.commodity_read_count)
    }

    inner class ViewAdapter(var list: ArrayList<State>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.receipt_commodity_comfirn_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.idView.text = item.id
            holder.expectCountView.text = item.expectCount
            holder.readCountView.text = item.readCount
        }

        override fun getItemCount(): Int = list.size
    }
}