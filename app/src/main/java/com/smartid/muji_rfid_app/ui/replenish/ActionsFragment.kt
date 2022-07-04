package com.smartid.muji_rfid_app.ui.replenish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.widgets.EntryBtnLayout

class ActionsFragment(private var state: ActionsState) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_replenish_fragment_actions, null, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        val firstBtn = view.findViewById<EntryBtnLayout>(R.id.replenish_btn_first)
        val secondBtn = view.findViewById<EntryBtnLayout>(R.id.replenish_btn_second)

        firstBtn.findViewWithTag<TextView>("TextView")?.text = state.firstBtnText
        secondBtn.findViewWithTag<TextView>("TextView")?.text = state.secondBtnText

        firstBtn.setOnClickListener {
            state.firstBtnOnClickCallback()
        }

        secondBtn.setOnClickListener {
            state.secondBtnOnClickCallback()
        }
    }
}