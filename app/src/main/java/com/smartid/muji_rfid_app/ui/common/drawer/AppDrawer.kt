package com.smartid.muji_rfid_app.ui.common.drawer

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.widgets.DrawerLayout
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils


class AppDrawer(activity: Activity) {
    private var _activity: Activity = activity

    private lateinit var _drawer: PopupWindow

    private lateinit var _view: View

    private var translateX = Utils.dip2px(_activity, -203f)


    fun show() {
        _view = DrawerLayout(_activity)

        if (StatusBarUtils.isNavigationBarExist(_activity)) {
            _view.setPadding(0, 0, 0, StatusBarUtils.getSystemBarHeight(_activity, StatusBarUtils.NAVIGARTION_BAR_HEIGHT))
        }

        _view.findViewById<LinearLayout>(R.id.drawer_layout).setPadding(0, StatusBarUtils.getSystemBarHeight(_activity, StatusBarUtils.STATUS_BAR_HEIGHT), 0, 0)

        _drawer = PopupWindow(_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true)
        _drawer.isClippingEnabled = false
        _drawer.isTouchable = true
        _drawer.isOutsideTouchable = false
        _drawer.setBackgroundDrawable(null)
        _drawer.showAtLocation(_view, Gravity.START, 0, 0)
        _drawer.update()

        _view.startAnimation(getInAnimation())

        _view.findViewById<Button>(R.id.drawer_close_btn).setOnClickListener {
            this.dismiss()
        }

    }

    private fun getInAnimation(): Animation {
        val translateAnimation: Animation = TranslateAnimation(translateX, 0f, 0f, 0f)
        translateAnimation.duration = 200
        translateAnimation.interpolator = AccelerateInterpolator()
        return translateAnimation
    }

    private fun getOutAnimation(): Animation {
        val translateAnimation: Animation = TranslateAnimation(0f, translateX, 0f, 0f)
        translateAnimation.duration = 200
        translateAnimation.interpolator = AccelerateInterpolator()
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                _drawer.dismiss()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        return translateAnimation
    }

    fun dismiss() {
        _view.startAnimation(getOutAnimation())
    }
}