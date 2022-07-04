package com.smartid.muji_rfid_app.ui.receipt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.RecyclerViewItemDecoration
import com.smartid.muji_rfid_app.ui.widgets.SizeList
import com.smartid.muji_rfid_app.utils.Utils

class ReceiptCommoditiesList(var view: RecyclerView, var list: ArrayList<State>, var status: Status = Status.NEW) {

    init {
        val adapter = ViewAdapter(list)
        val ll = LinearLayoutManager(view.context)
        ll.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = ll
        view.adapter = adapter
    }

    class State(
        var id: String = "",
        var name: String = "",
        var image: String = "",
        var areaSells: String = "",
        var storeSells: String = "",
        var isLock: Boolean = false,
        var lockedDate: String = "",
        var specs: String = "",
        var sizes: ArrayList<SizeList.SizeState> = ArrayList(),
        var exp: String = "",
        var price: String = "",
        var isNew: Boolean = false,
        var isTick: Boolean = false,
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.receipt_id)
        val nameView: TextView = view.findViewById(R.id.receipt_name)
        val imageView: ImageView = view.findViewById(R.id.receipt_image)
        val areaSellsView: TextView = view.findViewById(R.id.receipt_area_sells)
        val storeSellsView: TextView = view.findViewById(R.id.receipt_store_sells)
        val isLockView: TextView = view.findViewById(R.id.receipt_is_lock)
        val specsView: TextView = view.findViewById(R.id.receipt_spec)
        val sizesView: RecyclerView = view.findViewById(R.id.receipt_sizes_list)
        val expView: TextView = view.findViewById(R.id.receipt_exp)
        val priceView: TextView = view.findViewById(R.id.receipt_price)
        val isNewView: ImageView = view.findViewById(R.id.receipt_is_new)
        val isTickView: ImageView = view.findViewById(R.id.receipt_is_tick)
    }

    inner class ViewAdapter(var list: ArrayList<State>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.receipt_prepare_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            when (status) {
                Status.NEW -> {
                    holder.areaSellsView.visibility = View.GONE
                    holder.storeSellsView.visibility = View.GONE
                }
                Status.REPLENISH -> {
                    holder.isNewView.visibility = View.GONE
                }
                Status.LOCKED -> {
                    holder.areaSellsView.visibility = View.GONE
                    holder.storeSellsView.visibility = View.GONE
                    holder.isNewView.visibility = View.GONE
                }
            }

            val item = list[position]
            holder.idView.text = item.id
            holder.nameView.text = item.name
            // TODO
            // holder.imageView.setImageURI()
            holder.isLockView.isActivated = item.isLock
            holder.isLockView.text = item.lockedDate
            holder.specsView.text = item.specs
            holder.expView.text = item.exp
            holder.priceView.text = item.price
            // holder.isNewView.visibility = if (item.isNew) View.GONE else View.VISIBLE
            holder.isTickView.visibility = if (item.isTick) View.GONE else View.VISIBLE

            val sizesAdapter = SizeViewAdapter(item.sizes)
            val ll = LinearLayoutManager(holder.sizesView.context)
            ll.orientation = LinearLayoutManager.HORIZONTAL
            holder.sizesView.layoutManager = ll
            holder.sizesView.addItemDecoration(RecyclerViewItemDecoration(LinearLayoutManager.HORIZONTAL, Utils.dip2px(view.context, 4f).toInt()))
            holder.sizesView.adapter = sizesAdapter
        }

        override fun getItemCount(): Int = list.size
    }

    inner class SizeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameView: TextView = view.findViewById(R.id.receipt_size_name)
        var countView: TextView = view.findViewById(R.id.receipt_size_count)
    }

    inner class SizeViewAdapter(private var sizes: ArrayList<SizeList.SizeState>) : RecyclerView.Adapter<SizeViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.receipt_prepare_item_size, parent, false)
            return SizeViewHolder(view)
        }

        override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
            val item = sizes[position]

            holder.nameView.text = item.name
            holder.countView.text = item.count
        }

        override fun getItemCount(): Int = sizes.size
    }

    enum class Status {
        NEW, REPLENISH, LOCKED
    }
}