package com.smartid.muji_rfid_app.ui.move

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.smartid.muji_rfid_app.R

class TableListAdapter(activity: Context, private val resourceId: Int, data: List<TableState>) : ArrayAdapter<TableState>(activity, resourceId, data) {

    inner class ViewHolder(val nameView: TextView, val countView: TextView, val sizeView: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val nameView = view.findViewById<TextView>(R.id.table_cell_name)
            val countView = view.findViewById<TextView>(R.id.table_cell_count)
            val sizeView = view.findViewById<TextView>(R.id.table_cell_size)
            viewHolder = ViewHolder(nameView, countView, sizeView)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        if (item != null) {
            viewHolder.nameView.text = item.name
            viewHolder.countView.text = item.counts
            viewHolder.sizeView.text = item.size
        }

        return view
    }
}