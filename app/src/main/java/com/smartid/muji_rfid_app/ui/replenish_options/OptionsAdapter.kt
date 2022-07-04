package com.smartid.muji_rfid_app.ui.replenish_options

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.smartid.muji_rfid_app.R

class OptionsAdapter constructor(activity: Context, private val resourceId: Int, data: List<SelectionState>) : ArrayAdapter<SelectionState>(activity, resourceId, data) {

    inner class ViewHolder(val titleView: TextView, val linearLayout: LinearLayout)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val titleView = view.findViewById<TextView>(R.id.replenish_options_title)
            val linearLayout = view.findViewById<LinearLayout>(R.id.replenish_options_list)
            viewHolder = ViewHolder(titleView, linearLayout)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        if (item != null) {
            viewHolder.titleView.text = item.title

            viewHolder.linearLayout.removeAllViews()

            item.list.forEachIndexed { key, el ->
                val optionView = LayoutInflater.from(context).inflate(R.layout.activity_replenish_options_item, viewHolder.linearLayout, false)
                optionView.findViewById<TextView>(R.id.replenish_option_name).text = el.name

                val checkbox = optionView.findViewById<ImageView>(R.id.replenish_option_checkbox)

                checkbox.isActivated = el.isActive
                checkbox.setOnClickListener {
                    el.isActive = !el.isActive
                    checkbox.isActivated = el.isActive
                }

                if (key > 0) {
                    val dottedView = LayoutInflater.from(context).inflate(R.layout.common_dotted_line, viewHolder.linearLayout, false)
                    viewHolder.linearLayout.addView(dottedView)
                }

                // 添加进视图
                viewHolder.linearLayout.addView(optionView)
            }
        }

        return view
    }
}