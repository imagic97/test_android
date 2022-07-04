package com.smartid.muji_rfid_app.ui.common.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.utils.Utils

class AppToast(context: Context, text: String) {
    var toast: Toast

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.common_toast, null, false)
        view.findViewById<TextView>(R.id.toast_content).text = text

        toast = Toast(context)
        toast.view = view

        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.BOTTOM, 0, Utils.dip2px(context, 96f).toInt())
    }

    fun show() {
        toast.show()
    }
}