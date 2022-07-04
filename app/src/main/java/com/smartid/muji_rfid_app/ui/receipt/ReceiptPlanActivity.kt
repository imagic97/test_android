package com.smartid.muji_rfid_app.ui.receipt

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class ReceiptPlanActivity : BaseActivity() {

    private val list: ArrayList<ReceiptCalendar.State> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_plan)

        StatusBarUtils.immersiveStatusBar(this, findViewById<LinearLayout>(R.id.toolbar), 0f)
        initToolbar()

        initLit()
        initView()
    }

    private fun initView() {
        val view = findViewById<RecyclerView>(R.id.receipt_calendar_list)
        ReceiptCalendar(view, list)
    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货准备"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<LinearLayout>(R.id.toolbar_right)?.removeAllViews()
    }

    private fun initLit() {
        list.add(ReceiptCalendar.State("1", "12", "", intArrayOf()))
        list.add(ReceiptCalendar.State("2", "11", "", intArrayOf(1)))
        list.add(ReceiptCalendar.State("3", "11", "", intArrayOf(1, 2)))
        list.add(ReceiptCalendar.State("6", "8", "", intArrayOf(1, 2, 3)))
        list.add(ReceiptCalendar.State("7", "2", "", intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3)))
        list.add(ReceiptCalendar.State("8", "2", "", intArrayOf(1, 2, 3)))
        list.add(ReceiptCalendar.State("13", "2", "", intArrayOf(1)))
    }

}