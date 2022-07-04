package com.smartid.muji_rfid_app.ui.move

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import com.smartid.muji_rfid_app.R


class PopupOptions(context: Context) : Dialog(context, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(true)
        window?.setGravity(Gravity.BOTTOM)
        window!!.setBackgroundDrawableResource(R.color.transparent)

        val view = LayoutInflater.from(context).inflate(R.layout.activity_move_options_popup, null, false)
        setContentView(view)
    }

    private fun initEvent() {
        findViewById<Button>(R.id.popup_options_cancel).setOnClickListener {
            // TODO cancel
        }
        // 移动至卖场
        findViewById<LinearLayout>(R.id.popup_options_sells).setOnClickListener {
            // TODO cancel
        }
        // 移动至保留区
        findViewById<LinearLayout>(R.id.popup_options_retain).setOnClickListener {
            // TODO cancel
        }
        // 移动至仓库
        findViewById<LinearLayout>(R.id.popup_options_warehouse).setOnClickListener {
            // TODO cancel
        }
    }

    override fun show() {
        super.show()
        val layoutParams = window!!.attributes
        layoutParams.gravity = Gravity.BOTTOM
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = layoutParams

    }
}