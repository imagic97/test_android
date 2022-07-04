package com.smartid.muji_rfid_app.ui.move

import android.os.Bundle
import android.widget.Button
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class MoveActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move)
        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.fragment_content), 0f)

        initEvent()
        replaceFragment(MoveWhereFragment(), true)
    }

    private fun initEvent() {
        // 店内移动
        findViewById<Button>(R.id.move_in_shop_btn).setOnClickListener {
            // 清空回退栈
            supportFragmentManager.popBackStack(null, 1)
            replaceFragment(MoveWhereFragment(), true)
        }
        // 非销售区
        findViewById<Button>(R.id.move_non_sales_btn).setOnClickListener {
            // 清空回退栈
            supportFragmentManager.popBackStack(null, 1)
            replaceFragment(MoveActionsFragment(), true)
        }
        // 顾客保留
        findViewById<Button>(R.id.move_retain_btn).setOnClickListener {
            // 清空回退栈
            supportFragmentManager.popBackStack(null, 1)
            replaceFragment(MoveWhereFragment(), true)
        }
    }
}