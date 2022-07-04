package com.smartid.muji_rfid_app.ui.common

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.utils.Utils

class PopupSelectSignal(val activity: Activity, offsetView: View) : PopupWindow() {

    private var x: Int = 0

    private var y: Int = 0

    init {
        val view = this.activity.layoutInflater.inflate(R.layout.activity_move_table_popup, null, false)
        val location = Utils.getViewLocation(offsetView)
        x = location[0]
        y = location[1] - Utils.getViewLocation(view)[3]

        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        this.setBackgroundDrawable(ColorDrawable())
        isTouchable = true
        isOutsideTouchable = true
        isFocusable = true
        contentView = view
    }

    fun show() {
        showAtLocation(contentView, Gravity.NO_GRAVITY, x, y)
    }

}