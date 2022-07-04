package com.smartid.muji_rfid_app.ui.receipt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class ReceiptListActivity : BaseActivity() {
    private val list: ArrayList<ReceiptList.State> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_list)
        StatusBarUtils.immersiveStatusBar(this, findViewById<LinearLayout>(R.id.toolbar), 0f)
        initLit()
        initToolbar()
        initView()
    }


    private fun initView() {
        val view = findViewById<RecyclerView>(R.id.receipt_list)
        ReceiptList(view, list)
        val nextBtn = findViewById<Button>(R.id.receipt_next)

        nextBtn.setOnClickListener {
            val intent = Intent(this, ReceiptPrepareActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货准备"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<LinearLayout>(R.id.toolbar_right)?.removeAllViews()
    }

    private fun initLit() {
        list.add(ReceiptList.State("DB2021038932103", "12/3/21"))
        list.add(ReceiptList.State("DB2021038932103", "12/3/21"))
        list.add(ReceiptList.State("DB2021038932103", "12/3/21"))
        list.add(ReceiptList.State("DB2021038932103", "12/3/21"))
        list.add(ReceiptList.State("DB2021038932103", "12/3/21"))
        list.add(ReceiptList.State("DB2021038932103", "12/3/21"))
        list.add(ReceiptList.State("DB2021038932103", "12/3/21"))
    }
}