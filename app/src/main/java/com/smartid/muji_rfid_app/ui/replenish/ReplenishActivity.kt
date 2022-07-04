package com.smartid.muji_rfid_app.ui.replenish

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class ReplenishActivity : BaseActivity() {
    private var isRFIDActions = object : ActionsState {
        override var firstBtnText: String = "RFID补充"
        override var secondBtnText: String = "非RFID补充"

        override fun firstBtnOnClickCallback() {
            val intent = Intent("android.intent.action.replenish_record")
                .addCategory("android.intent.category.replenish_record")
            startActivity(intent)
        }

        override fun secondBtnOnClickCallback() {
            val intent = Intent("android.intent.action.replenish_details")
                .addCategory("android.intent.category.replenish_details")
            startActivity(intent)
        }
    }


    private var sourceActions = object : ActionsState {
        override var firstBtnText: String = "日常商品补充"
        override var secondBtnText: String = "来货商品补充"

        override fun firstBtnOnClickCallback() {
            replaceFragment(ActionsFragment(isRFIDActions))
        }

        override fun secondBtnOnClickCallback() {
            replaceFragment(ActionsFragment(isRFIDActions))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replenish)
        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.toolbar), 0f)
        init()
        replaceFragment(ActionsFragment(sourceActions))
    }

    private fun init() {
        findViewById<TextView>(R.id.toolbar_title).text = "商品补充"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<TextView>(R.id.toolbar_menu).text = "OK"
        // 设置透明色
        val toolbar = findViewById<LinearLayout>(R.id.toolbar)
        toolbar.background = null
        toolbar.setBackgroundColor(Color.TRANSPARENT)
    }
}