package com.smartid.muji_rfid_app.ui.receipt

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.RecyclerViewItemDecoration
import com.smartid.muji_rfid_app.utils.Utils

class ReceiptCalendar(var view: RecyclerView, var list: ArrayList<State>) {

    init {
        val statusAdapter = ViewAdapter(list)
        val ll = LinearLayoutManager(view.context)
        ll.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = ll
        view.adapter = statusAdapter
    }

    class State(
        var day: String,
        var month: String,
        var category: String,
        var statusList: IntArray,
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayView: TextView = view.findViewById(R.id.receipt_calendar_day)
        val monthView: TextView = view.findViewById(R.id.receipt_calendar_month)
        val statusView: RecyclerView = view.findViewById(R.id.receipt_calendar_status_list)
    }

    inner class ViewAdapter(var list: ArrayList<State>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.receipt_calendar_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]

            holder.dayView.text = item.day
            holder.monthView.text = "${item.month}月"

            val statusAdapter = StatusViewAdapter(item.statusList)
            val ll = LinearLayoutManager(holder.statusView.context)
            ll.orientation = LinearLayoutManager.HORIZONTAL
            holder.statusView.layoutManager = ll
            holder.statusView.addItemDecoration(RecyclerViewItemDecoration(LinearLayoutManager.HORIZONTAL, Utils.dip2px(holder.statusView.context, 1f).toInt()))
            holder.statusView.adapter = statusAdapter
        }

        override fun getItemCount(): Int = list.size
    }

    inner class StatusViewHolder(var view: ImageView) : RecyclerView.ViewHolder(view)

    inner class StatusViewAdapter(var list: IntArray) : RecyclerView.Adapter<StatusViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
            val view = ImageView(parent.context)
            val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            layoutParams.width = Utils.dip2px(parent.context, 50f).toInt()
            layoutParams.height = Utils.dip2px(parent.context, 44f).toInt()
            view.layoutParams = layoutParams
            view.scaleType = ImageView.ScaleType.CENTER
            return StatusViewHolder(view)
        }

        override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
            holder.view.setOnClickListener {
                val intent = Intent(holder.view.context, ReceiptListActivity::class.java)
                holder.view.context.startActivity(intent)
            }

            when (list[position]) {
                1 -> {
                    // 仓库未发货
                    holder.view.setImageDrawable(createDrawable(holder.view.context, R.drawable.ic_plan_not_shipped, "#7f0019"))
                }
                2 -> {
                    // 门店未收货
                    holder.view.setImageDrawable(createDrawable(holder.view.context, R.drawable.ic_plan_not_received, "#7f0019"))
                }
                3 -> {
                    // 仓库已发货
                    holder.view.setImageDrawable(createDrawable(holder.view.context, R.drawable.ic_plan_received, "#7f0019"))
                }
            }

        }

        override fun getItemCount(): Int = list.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun createDrawable(context: Context, resId: Int, color: String): Drawable {
        val image = context.resources.getDrawable(resId, null)
        image.setTint(Color.parseColor(color))
        return image
    }
}