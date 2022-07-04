package com.smartid.muji_rfid_app.ui.replenish_sells

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.ui.move.TableListAdapter
import com.smartid.muji_rfid_app.ui.move.TableState
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class ReplenishSellsActivity : BaseActivity() {

    private lateinit var listView: ListView

    private val list = ArrayList<TableState>()

    private lateinit var tableListAdapter: TableListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replenish_sells)

        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.toolbar), 0.0f)

        initList()
        initView()
    }

    private fun initView() {
        findViewById<TextView>(R.id.toolbar_title).text = "补货移动至卖场"
        findViewById<TextView>(R.id.toolbar_menu).text = "提交"
        findViewById<Button>(R.id.toolbar_back_btn).setOnClickListener {
            finish()
        }

        findViewById<LinearLayout>(R.id.toolbar_right).removeAllViews()

        listView = findViewById(R.id.table_list_view)

        tableListAdapter = TableListAdapter(this, R.layout.activity_move_table_item, list)
        listView.adapter = tableListAdapter
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