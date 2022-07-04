package com.smartid.muji_rfid_app.ui.move

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R

class CommoditiesListFragment(private var title: String) : Fragment() {

    private lateinit var listView: ListView
    private val list = ArrayList<CommoditiesListState>()

    private lateinit var commoditiesListAdapter: CommoditiesListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_move_fragment_list, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.toolbar_title).text = title
        view.findViewById<LinearLayout>(R.id.toolbar_right).removeAllViews()
        view.findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            (activity as MoveActivity).onBackPressed()
        }

        view.findViewById<Button>(R.id.commodities_list_move_out_btn).setOnClickListener {
            PopupOptions(view.context).show()
        }

        initList()
        commoditiesListAdapter = CommoditiesListAdapter(requireContext(), R.layout.activity_move_list_item, list)

        listView = view.findViewById(R.id.commodities_list_view)

        listView.adapter = commoditiesListAdapter
    }

    fun initList() {
        for (index in 1..3) {
            var commoditiesList: ArrayList<CommodityState> = ArrayList()
            for (key in 1..3) {
                commoditiesList.add(CommodityState("4523354677781-${index}", "条纹衬衫$key", "藏青色", false))
            }

//            var commoditiesListState: CommoditiesListState = CommoditiesListState("男装", commoditiesList)
            list.add(CommoditiesListState("男装", commoditiesList))

        }

    }
}