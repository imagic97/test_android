package com.smartid.muji_rfid_app.ui.replenish_record

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.smartid.muji_rfid_app.R

class RecordAdapter constructor(activity: Context, private val resourceId: Int, data: List<RecordState>) : ArrayAdapter<RecordState>(activity, resourceId, data) {

    inner class ViewHolder(val stateView: ImageView, val dateView: TextView, val sellsCountsView: TextView, val replenishCountsView: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val stateView = view.findViewById<ImageView>(R.id.record_item_state)
            val dateView = view.findViewById<TextView>(R.id.record_item_date)
            val sellsCountsView = view.findViewById<TextView>(R.id.record_item_sells)
            val replenishCountsView = view.findViewById<TextView>(R.id.record_item_replenish)
            viewHolder = ViewHolder(stateView, dateView, sellsCountsView, replenishCountsView)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        if (item != null) {
            when (item.state) {
                RecordState.FINISH -> {
                    viewHolder.stateView.isActivated = true
                    viewHolder.stateView.isHovered = false
                }
                RecordState.PART -> {
                    viewHolder.stateView.isActivated = false
                    viewHolder.stateView.isHovered = true
                }
                RecordState.READY -> {
                    viewHolder.stateView.isHovered = false
                    viewHolder.stateView.isActivated = false
                }
            }

            viewHolder.dateView.text = item.date
            viewHolder.sellsCountsView.text = item.sell_count
            viewHolder.replenishCountsView.text = item.replenish_count
        }

        return view
    }
}