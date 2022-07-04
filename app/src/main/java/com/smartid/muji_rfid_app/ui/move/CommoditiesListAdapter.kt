package com.smartid.muji_rfid_app.ui.move

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.smartid.muji_rfid_app.R

class CommoditiesListAdapter(activity: Context, private val resourceId: Int, data: List<CommoditiesListState>) : ArrayAdapter<CommoditiesListState>(activity, resourceId, data) {

    inner class ViewHolder(val categoryView: TextView, val linearLayout: LinearLayout, val checkboxView: ImageView)

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val checkboxView = view.findViewById<ImageView>(R.id.list_item_checkbox)
            val categoryView = view.findViewById<TextView>(R.id.list_item_category)
            val linearLayout = view.findViewById<LinearLayout>(R.id.move_list_item_content)
            viewHolder = ViewHolder(categoryView, linearLayout, checkboxView)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        if (item != null) {
            viewHolder.categoryView.text = item.category
            viewHolder.checkboxView.isActivated = item.isActive

            viewHolder.checkboxView.isActivated = item.isActive
            viewHolder.checkboxView.setOnClickListener {
                item.isActive = !item.isActive
                viewHolder.checkboxView.isActivated = item.isActive
                item.list.forEach { el ->
                    el.isActive = item.isActive
                    this.notifyDataSetChanged()
                }
            }

            viewHolder.linearLayout.removeAllViews()
            item.list.forEachIndexed { key, state ->
                // 获取商品信息布局文件， 设置父级为 linearLayout
                val commodityView = LayoutInflater.from(context).inflate(R.layout.activity_move_list_commodity_info, viewHolder.linearLayout, false)

                // 设置商品信息
                //  commodityView.findViewById<ImageView>(R.id.move_commodity_name).setImageURI() TODO 设置商品图
                commodityView.findViewById<TextView>(R.id.move_commodity_name).text = state.name
                commodityView.findViewById<TextView>(R.id.move_commodity_size).text = state.size
                commodityView.findViewById<TextView>(R.id.move_commodity_spec).text = state.specs
                val commoditiesCheckbox = commodityView.findViewById<ImageView>(R.id.move_commodity_checkbox)
                commoditiesCheckbox.isActivated = state.isActive
                commoditiesCheckbox.setOnClickListener {
                    state.isActive = !state.isActive
                    commoditiesCheckbox.isActivated = state.isActive
                    // 判断是否全部选择/未选
                    val isAllSelected = item.list.all { it.isActive }
                    item.isActive = isAllSelected
                    viewHolder.checkboxView.isActivated = isAllSelected
                }

                // 商品数量大于 1 时需要添加分割线
                if (key >= 1) {
                    val dottedView = LayoutInflater.from(context).inflate(R.layout.common_dotted_line, viewHolder.linearLayout, false)
                    viewHolder.linearLayout.addView(dottedView)
                }

                // 添加进视图
                viewHolder.linearLayout.addView(commodityView)
            }
        }

        return view
    }
}