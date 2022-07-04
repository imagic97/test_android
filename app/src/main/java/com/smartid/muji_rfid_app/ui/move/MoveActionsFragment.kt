package com.smartid.muji_rfid_app.ui.move

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.widgets.EntryBtnLayout

class MoveActionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_move_fragment_move_actions, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.toolbar_title).text = "非销售区"

        view.findViewById<LinearLayout>(R.id.toolbar_right).removeAllViews()

        view.findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            (activity as MoveActivity).onBackPressed()
        }

        view.findViewById<EntryBtnLayout>(R.id.action_move_out).setOnClickListener {
            (activity as MoveActivity).replaceFragment(CommoditiesListFragment("移出非销售区"))
        }

        view.findViewById<EntryBtnLayout>(R.id.action_move_in).setOnClickListener {
            (activity as MoveActivity).replaceFragment(TableViewFragment("移入非销售区"))
        }
    }
}