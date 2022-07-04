package com.smartid.muji_rfid_app.ui.receipt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R

class ReceiptList(var view: RecyclerView, var list: ArrayList<State>) {

    init {
        val statusAdapter = ViewAdapter(list)
        val ll = LinearLayoutManager(view.context)
        ll.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = ll
        view.adapter = statusAdapter
    }

    class State(
        var id: String,
        var date: String,
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.receipt_list_id)
        val dateView: TextView = view.findViewById(R.id.receipt_list_date)
    }

    inner class ViewAdapter(var list: ArrayList<State>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.receipt_list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.dateView.visibility = View.VISIBLE
            holder.idView.text = item.id
            holder.dateView.text = item.date
        }

        override fun getItemCount(): Int = list.size
    }
}