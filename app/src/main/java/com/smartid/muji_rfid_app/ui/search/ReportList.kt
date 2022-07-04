package com.smartid.muji_rfid_app.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.widgets.SizeList

class ReportList(val view: RecyclerView, val list: ArrayList<State>) {

    init {
        val layoutManager = LinearLayoutManager(view.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.addItemDecoration(ReportItemDecoration(view.context, createGroupBeans(list)))
        view.layoutManager = layoutManager
        view.adapter = Adapter(list)
    }


    private fun createGroupBeans(list: ArrayList<State>): ArrayList<ReportItemDecoration.GroupBean?> {
        val groupBeans: ArrayList<ReportItemDecoration.GroupBean?> = ArrayList()
        for (data in list) {
            val i: Int = list.indexOf(data)
            val groupId = i / 4
            val groupPosition = i % 4
            var groupBean: ReportItemDecoration.GroupBean? = null

            if (groupPosition == 0) {
                groupBean = ReportItemDecoration.GroupBean(groupId, groupPosition, true, false)
            }
            if (groupPosition == 3) {
                groupBean = ReportItemDecoration.GroupBean(groupId, groupPosition, false, true)
            }
            groupBeans.add(groupBean)
        }
        return groupBeans
    }


    inner class Adapter(val list: ArrayList<State>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_stock_report_item, parent, false)
            return Holder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val item = list[position]
            // TODO 设置头图
            // (holder as Holder).commodityImage.setImageURI()
            (holder as Holder).commodityName.text = item.name
            holder.commodityPrefixId.text = item.id.substring(0, 7)
            holder.commoditySuffixId.text = "-${item.id.substring(7)}"
            holder.commodityName.text = item.name
            holder.commodityPrice.text = item.price
            holder.commoditySpec.text = item.spec
            holder.commodityIsActive.isActivated = item.isActive
            holder.commodityDays.text = "【${item.days}天】"
            holder.commodityWeekSales.text = item.weekSales
            holder.commodityTotalSales.text = item.totalSales

            SizeList(holder.detailsSizes, item.sizes)
        }

        override fun getItemCount(): Int = list.size
    }


    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var commodityImage: ImageView = view.findViewById(R.id.commodity_image)
        var commodityPrefixId: TextView = view.findViewById(R.id.commodity_prefix_id)
        var commoditySuffixId: TextView = view.findViewById(R.id.commodity_suffix_id)
        var commodityName: TextView = view.findViewById(R.id.commodity_name)
        var commodityPrice: TextView = view.findViewById(R.id.commodity_price)
        var commoditySpec: TextView = view.findViewById(R.id.commodity_spec)
        var commodityIsActive: ImageView = view.findViewById(R.id.commodity_is_active)
        var commodityDays: TextView = view.findViewById(R.id.commodity_days)
        var commodityWeekSales: TextView = view.findViewById(R.id.commodity_week_sales)
        var commodityTotalSales: TextView = view.findViewById(R.id.commodity_total_sales)
        var detailsSizes: RecyclerView = view.findViewById(R.id.details_sizes)
    }

    class State(
        var categoryName: String,
        var secondaryCategoryName: String,
        var image: String,
        var id: String,
        var name: String,
        var price: String,
        var spec: String,
        var days: String,
        var weekSales: String,
        var totalSales: String,
        var isActive: Boolean,
        var sizes: ArrayList<SizeList.SizeState>
    )
}