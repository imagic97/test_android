package com.smartid.muji_rfid_app.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.Application
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.RecyclerViewItemDecoration
import com.smartid.muji_rfid_app.utils.Utils


class OtherStoreSpecsList(var view: RecyclerView, var list: ArrayList<String>) {

    init {
        val linearLayoutManager = LinearLayoutManager(view.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        view.layoutManager = linearLayoutManager
        view.adapter = SpecsViewAdapter(list)
        view.addItemDecoration(RecyclerViewItemDecoration(LinearLayoutManager.HORIZONTAL, Application.getContext().resources.getDimension(R.dimen.dp4).toInt()))
    }

    private inner class SpecsViewHolder(var view: TextView) : RecyclerView.ViewHolder(view)

    private inner class SpecsViewAdapter(var specs: ArrayList<String>) : RecyclerView.Adapter<SpecsViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecsViewHolder {
            val button = TextView(view.context)
            val layoutParams = ViewGroup.LayoutParams(Utils.dip2px(view.context, 50f).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
            button.layoutParams = layoutParams
            val padding = Utils.dip2px(view.context, 5f).toInt()
            button.setPadding(0, padding, 0, padding)
            button.gravity = Gravity.CENTER
            button.background = createStateListDrawable(view.context)
            button.setTextColor(createStateListTextColor())
            return SpecsViewHolder(button)
        }

        override fun onBindViewHolder(holder: SpecsViewHolder, position: Int) {
            val item: String = specs[position]
            holder.view.text = item
        }

        override fun getItemCount(): Int {
            return specs.size
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun createStateListDrawable(context: Context): StateListDrawable {
        val bg = StateListDrawable()
        val normal = context.resources.getDrawable(R.drawable.shape_search_btn_normal, null)
        val less = context.resources.getDrawable(R.drawable.shape_search_btn_less, null)
        val warn = context.resources.getDrawable(R.drawable.shape_search_btn_warn, null)

        bg.addState(intArrayOf(android.R.attr.state_activated), less)
        bg.addState(intArrayOf(android.R.attr.state_focused), warn)
        bg.addState(intArrayOf(), normal)

        return bg
    }

    private fun createStateListTextColor(): ColorStateList {
        val normal = Color.parseColor("#13BB7C")
        val less = Color.parseColor("#FF9900")
        val warn = Color.parseColor("#B6B9BF")

        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_activated),
                intArrayOf(android.R.attr.state_focused), intArrayOf()
            ),
            intArrayOf(less, warn, normal)
        )
    }
}