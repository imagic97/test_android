package com.smartid.muji_rfid_app.ui.receipt


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.ui.widgets.EntryBtnLayout
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class ReceiptActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)
        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.toolbar), 0f)
        initToolbar()
        initView()
    }

    private fun initView() {
        findViewById<EntryBtnLayout>(R.id.entry_receipt_plan).setOnClickListener {
            val intent = Intent(this, ReceiptPlanActivity::class.java)
            startActivity(intent)
        }

        findViewById<EntryBtnLayout>(R.id.entry_receipt_process).setOnClickListener {
            val intent = Intent(this, ReceiptPackageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货入库"
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