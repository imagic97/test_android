package com.smartid.muji_rfid_app.ui.replenish_details

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.widgets.SizeList

class CommodityAdapter(var commodities: ArrayList<CommodityState>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_replenish_details_item, parent, false)
        context = parent.context
        return CommodityHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = commodities[position]

        (holder as CommodityHolder).activityName.text = item.activityName
        holder.activityYear.text = item.activityYear
        holder.categoryName.text = item.categoryName
        holder.secondaryCategoryName.text = item.secondaryCategoryName
        // TODO 设置头图
//        holder.image.setImageResource()
        holder.prefixId.text = item.prefixId
        holder.suffixId.text = "-${item.suffixId}"
        holder.originPrice.text = item.originPrice
        holder.originPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.price.text = item.price
        holder.spec.text = item.spec
        holder.isUrgent.isActivated = item.isUrgent
        holder.isSameDay.isActivated = item.isSameDay
        holder.isActive.isActivated = item.isActive

        // checkbox 设置点击事件
        holder.isActive.setOnClickListener {
            item.isActive = !item.isActive
            holder.isActive.isActivated = item.isActive
        }

        SizeList(holder.sizesView, item.sizes)
    }

    override fun getItemCount(): Int = commodities.size
}