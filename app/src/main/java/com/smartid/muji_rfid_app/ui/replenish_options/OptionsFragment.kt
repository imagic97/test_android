package com.smartid.muji_rfid_app.ui.replenish_options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R

class OptionsFragment(var list: ArrayList<SelectionState>) : Fragment() {

    lateinit var listAdapter: OptionsAdapter
    lateinit var listView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_replenish_options_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        listView = view.findViewById(R.id.replenish_options_list_view)

        listAdapter = OptionsAdapter(requireActivity(), R.layout.activity_replenish_options_fragment_item, list)

        listView.adapter = listAdapter
    }
}