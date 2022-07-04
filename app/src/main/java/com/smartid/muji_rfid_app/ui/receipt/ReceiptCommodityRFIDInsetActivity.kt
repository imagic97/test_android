package com.smartid.muji_rfid_app.ui.receipt

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class ReceiptCommodityRFIDInsetActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_commodity_rfid_inset)
        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.toolbar), 0f)
        initToolbar()
        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.receipt_scan_btn).setOnClickListener {
            val intent = Intent(this, ReceiptCommodityInfoInsetActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货-RFID植入"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<LinearLayout>(R.id.toolbar_right)?.removeAllViews()
        // 设置透明色
        val toolbar = findViewById<LinearLayout>(R.id.toolbar)
        toolbar.background = null
        toolbar.setBackgroundColor(Color.TRANSPARENT)
    }
}