package com.smartid.muji_rfid_app.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.ui.common.drawer.AppDrawer
import com.smartid.muji_rfid_app.ui.search.SearchActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.main_toolbar), 0f)
        var menuBtn: Button = findViewById(R.id.main_menu_btn)

        menuBtn.setOnClickListener {
            AppDrawer(this).show()
        }

        findViewById<Button>(R.id.main_gps_btn).setOnClickListener {
            var intent = Intent("android.intent.action.login")
                .addCategory("android.intent.category.login")

            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.entry_move).setOnClickListener {
            val intent = Intent("android.intent.action.move")
                .addCategory("android.intent.category.move")
            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.entry_replenish).setOnClickListener {
            val intent = Intent("android.intent.action.replenish")
                .addCategory("android.intent.category.replenish")
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.entry_search).setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.entry_receipt).setOnClickListener {
            val intent = Intent("android.intent.action.receipt")
                .addCategory("android.intent.category.receipt")
            startActivity(intent)
        }
    }


}
