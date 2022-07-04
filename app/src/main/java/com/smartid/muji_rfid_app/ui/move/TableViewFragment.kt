package com.smartid.muji_rfid_app.ui.move

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.PopupSelectSignal

class TableViewFragment(title: String) : Fragment() {

    private var title = title
    private lateinit var listView: ListView

    private val list = ArrayList<TableState>()

    private var popup: PopupSelectSignal? = null

    private lateinit var tableListAdapter: TableListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_move_fragment_table, container, false)

        listView = view.findViewById(R.id.table_list_view)

        initList()
        tableListAdapter = TableListAdapter(requireContext(), R.layout.activity_move_table_item, list)

        val tabSignal = view.findViewById<ImageView>(R.id.tab_signal)
        tabSignal.setOnClickListener {
            if (popup == null) {
                popup = PopupSelectSignal(context as Activity, tabSignal)
            }
            popup?.show()
        }

        listView.adapter = tableListAdapter
        initView(view)
        return view
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.toolbar_title).text = title

        val submit = view.findViewById<TextView>(R.id.toolbar_menu)
        submit.text = "提交"
        submit.setOnClickListener {
            PopupDialog.confirm(requireContext(), "读取到顾客保留库位的商品：\n条纹衬衫\n3035ABDCD0002A0840000064").show()
        }

        view.findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            (activity as MoveActivity).onBackPressed()
        }
    }

    override fun onDestroy() {
        // 存在弹出窗时关闭
        popup?.dismiss()
        super.onDestroy()
    }


    private fun initList() {
        list.add(TableState("4523354677789", "58", "S"))
        list.add(TableState("4523354677789", "58", "S"))
        list.add(TableState("4523354677789", "58", "S"))
        list.add(TableState("4523354677789", "58", "S"))
        list.add(TableState("4523354677789", "58", "S"))
        list.add(TableState("4523354677789", "58", "S"))
        list.add(TableState("4523354677789", "58", "S"))
        list.add(TableState("4523354677789", "58", "S"))
        list.add(TableState("4523354677789", "58", "S"))
    }
}