package com.smartid.muji_rfid_app.ui.receipt

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.ui.search.SpecsInfo
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class ReceiptCommodityInfoInsetActivity : BaseActivity() {
    private var list: ArrayList<SpecsInfo.State>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_commodity_info_inset)


        StatusBarUtils.immersiveStatusBar(this, findViewById<LinearLayout>(R.id.toolbar), 0f)
        initToolbar()

        initList()
        initView()
    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货-RFID植入"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<TextView>(R.id.toolbar_menu).text = "确定"
    }

    private fun initView() {
        val recyclerView = findViewById<RecyclerView>(R.id.receipt_commodity_info_list)
        SpecsInfo(recyclerView, list!!)
    }

    private fun initList() {
        list = ArrayList()
        list!!.add(SpecsInfo.State("IMAGE", "4550344731161"))
        list!!.add(SpecsInfo.State("部        门：", "022 WOMEN"))
        list!!.add(SpecsInfo.State("L  I  N  E ：", "013"))
        list!!.add(SpecsInfo.State("C L A S S：", "013"))
        list!!.add(SpecsInfo.State("条        码：", "4523354677789"))
        list!!.add(SpecsInfo.State("集约条码：", "4523354677789"))
        list!!.add(SpecsInfo.State("RFID编码：", "DB2021038932103"))
        list!!.add(SpecsInfo.State("状        态：", "柬埔寨"))
        list!!.add(SpecsInfo.State("库        位：", "卖场区"))
    }
}