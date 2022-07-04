package com.smartid.muji_rfid_app.ui.receipt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.ui.common.PopupSelectSignal
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class ReceiptCommodityConfirmActivity : BaseActivity() {
    private val list: ArrayList<ReceiptCommodityList.State> = ArrayList()

    private var popupSelectSignal: PopupSelectSignal? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_commodity_comfirn)

        StatusBarUtils.immersiveStatusBar(this, findViewById<LinearLayout>(R.id.toolbar), 0f)
        initToolbar()

        initLit()
        initView()
    }

    private fun initView() {
        val view = findViewById<RecyclerView>(R.id.receipt_commodities_list)
        ReceiptCommodityList(view, list)

        val popupBtn = findViewById<ImageButton>(R.id.receipt_popup_btn)
        popupBtn.setOnClickListener {
            if (popupSelectSignal == null) {
                popupSelectSignal = PopupSelectSignal(this, findViewById(R.id.receipt_action_bar))
            }
            popupSelectSignal?.show()
        }

        val scanBtn = findViewById<Button>(R.id.receipt_scan_btn)
        scanBtn.setOnClickListener {
            val intent = Intent(this, ReceiptCommodityEditActivity::class.java)
            intent.putExtra("value", false)
            startActivity(intent)
        }
        val readBtn = findViewById<Button>(R.id.receipt_read_btn)
        readBtn.setOnClickListener {
            val intent = Intent(this, ReceiptCommodityEditActivity::class.java)
            intent.putExtra("value", true)
            startActivity(intent)
        }

        val nextBtn = findViewById<ImageButton>(R.id.receipt_next_btn)
        nextBtn.setOnClickListener { }

    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货入库"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<LinearLayout>(R.id.toolbar_right)?.removeAllViews()
    }

    private fun initLit() {
        list.add(ReceiptCommodityList.State("4523354677789", "35", "5"))
        list.add(ReceiptCommodityList.State("4523354677789", "35", "5"))
        list.add(ReceiptCommodityList.State("4523354677789", "35", "5"))
        list.add(ReceiptCommodityList.State("4523354677789", "35", "5"))
        list.add(ReceiptCommodityList.State("4523354677789", "35", "5"))
        list.add(ReceiptCommodityList.State("4523354677789", "35", "5"))
        list.add(ReceiptCommodityList.State("4523354677789", "35", "5"))
    }
}