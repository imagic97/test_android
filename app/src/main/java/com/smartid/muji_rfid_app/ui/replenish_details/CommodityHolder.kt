package com.smartid.muji_rfid_app.ui.replenish_details

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R

class CommodityHolder(view: View) : RecyclerView.ViewHolder(view) {
    var activityName: TextView = view.findViewById(R.id.replenish_activity_name)
    var activityYear: TextView = view.findViewById(R.id.replenish_activity_year)
    var categoryName: TextView = view.findViewById(R.id.replenish_category_name)
    var secondaryCategoryName: TextView = view.findViewById(R.id.replenish_secondary_category_name)
    var image: ImageView = view.findViewById(R.id.replenish_commodity_image)
    var prefixId: TextView = view.findViewById(R.id.replenish_commodity_prefix_id)
    var suffixId: TextView = view.findViewById(R.id.replenish_commodity_suffix_id)
    var originPrice: TextView = view.findViewById(R.id.replenish_commodity_origin_price)
    var price: TextView = view.findViewById(R.id.replenish_commodity_price)
    var spec: TextView = view.findViewById(R.id.replenish_commodity_spec)
    var isUrgent: ImageView = view.findViewById(R.id.replenish_commodity_is_urgent)
    var isSameDay: ImageView = view.findViewById(R.id.replenish_commodity_is_same_day)
    var isActive: ImageView = view.findViewById(R.id.replenish_commodity_checkbox)
    var sizesView: RecyclerView = view.findViewById(R.id.replenish_details_sizes_view)
//    var category: TextView= view.findViewById(R.id.replenish_commodity_category)
}