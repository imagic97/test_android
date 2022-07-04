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

class MoveWhereFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_move_fragment_move_where, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.toolbar_title).text = "店内移动"

        view.findViewById<LinearLayout>(R.id.toolbar_right).removeAllViews()

        view.findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            (activity as MoveActivity).onBackPressed()
        }

        view.findViewById<EntryBtnLayout>(R.id.action_move_warehouse).setOnClickListener {
            (activity as MoveActivity).replaceFragment(TableViewFragment("移动至仓库"))
        }

        view.findViewById<EntryBtnLayout>(R.id.action_move_sells).setOnClickListener {
            (activity as MoveActivity).replaceFragment(TableViewFragment("移动至卖场"))
        }
    }
}