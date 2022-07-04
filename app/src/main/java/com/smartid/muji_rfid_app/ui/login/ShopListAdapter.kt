package com.smartid.muji_rfid_app.ui.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.smartid.muji_rfid_app.R

class ShopListAdapter(activity: Context, private val resourceId: Int, data: List<Shop>) : ArrayAdapter<Shop>(activity, resourceId, data) {

    inner class ViewHolder(val idView: TextView, val nameView: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        context

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val idView = view.findViewById<TextView>(R.id.shop_id)
            val nameView = view.findViewById<TextView>(R.id.shop_name)
            viewHolder = ViewHolder(idView, nameView)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        if (item != null) {
            viewHolder.idView.text = item.id
            viewHolder.nameView.text = item.name
        }

        return view
    }
}