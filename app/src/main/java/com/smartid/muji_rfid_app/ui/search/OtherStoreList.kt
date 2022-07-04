package com.smartid.muji_rfid_app.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.Application
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.RecyclerViewItemDecoration

class OtherStoreList(var view: RecyclerView, var list: ArrayList<StoreState>, var callback: Fragment) {

    init {
        val linearLayoutManager = LinearLayoutManager(view.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = linearLayoutManager
        view.adapter = Adapter(list)
        view.addItemDecoration(RecyclerViewItemDecoration(LinearLayoutManager.VERTICAL, Application.getContext().resources.getDimension(R.dimen.dp8).toInt()))
    }

    class StoreState(var id: String, val name: String, val tel: String, val specs: ArrayList<String>)

    private inner class ViewHolder(var view: LinearLayout) : RecyclerView.ViewHolder(view) {
        var idView: TextView = view.findViewById(R.id.other_store_id)
        var nameView: TextView = view.findViewById(R.id.other_store_name)
        var telView: TextView = view.findViewById(R.id.other_store_tel)
        var specsListView: RecyclerView = view.findViewById(R.id.other_store_btns_recycler)
    }

    private inner class Adapter(var list: ArrayList<StoreState>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(view.context).inflate(R.layout.fragment_search_other_store_item, parent, false)
            return ViewHolder(view as LinearLayout)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item: StoreState = list[position]

            holder.idView.text = item.id
            holder.nameView.text = item.name
            holder.telView.text = "电话：${item.tel}"

            OtherStoreSpecsList(holder.specsListView, item.specs)

            holder.view.setOnClickListener {
                (callback.requireActivity() as SearchActivity).replaceFragment(OtherStoreDetailsFragment())
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
}