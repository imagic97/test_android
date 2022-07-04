package com.smartid.muji_rfid_app.ui.common.loading

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.PopupWindow
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity

class AppLoading(val activity: BaseActivity) {

    private var _loading: PopupWindow

    private var view: View = this.activity.layoutInflater.inflate(R.layout.common_loading, null, false)

    init {
        val imageView = view.findViewById<ImageView>(R.id.loading_image)
        val animation = AnimationUtils.loadAnimation(imageView.context, R.anim.rotate)
        imageView.startAnimation(animation)

        _loading = PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        _loading.isTouchable = true
        _loading.isOutsideTouchable = false
        _loading.setBackgroundDrawable(null)
    }

    fun show() {
        this.activity.setCurrentPopupWindow(_loading)
        _loading.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    fun dismiss() {
        _loading.dismiss()
    }
}
