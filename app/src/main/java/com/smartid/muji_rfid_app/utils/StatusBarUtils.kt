package com.smartid.muji_rfid_app.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout

class StatusBarUtils {

    companion object {
        const val STATUS_BAR_HEIGHT = "status_bar_height"
        const val NAVIGARTION_BAR_HEIGHT = "navigation_bar_height"


//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        fun immersiveStatusBar(activity: Activity, toolbar: View?, alpha: Float, isFullScreen: Boolean = false) {
            val window = activity.window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
            val decorView = window.decorView as ViewGroup
            val contentView = window.decorView.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
            val rootView = contentView.getChildAt(0)
            if (rootView != null) {
                rootView.fitsSystemWindows = false
            }
            val statusBarHeight = getSystemBarHeight(activity, STATUS_BAR_HEIGHT)
            toolbar?.layoutParams?.height = toolbar?.layoutParams?.height?.plus(statusBarHeight)
            toolbar?.setPadding(toolbar.paddingLeft, toolbar.paddingTop + statusBarHeight, toolbar.paddingRight, toolbar.paddingBottom)
            decorView.addView(createStatusBarView(activity, alpha))
        }

        private fun createStatusBarView(activity: Activity, alpha: Float): View {
            val statusBarView = View(activity)
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getSystemBarHeight(activity, STATUS_BAR_HEIGHT))
            statusBarView.layoutParams = params
            statusBarView.setBackgroundColor(Color.argb((alpha * 255).toInt(), 0, 0, 0))
            return statusBarView
        }

        fun getSystemBarHeight(context: Context, key: String): Int {
            var result = 0
            val resourceId: Int = context.resources.getIdentifier(key, "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
            return result
        }

        fun isNavigationBarExist(activity: Activity): Boolean {
            val height: Int = getSystemBarHeight(activity, NAVIGARTION_BAR_HEIGHT)
            val insetsBottom = activity.window.decorView.rootWindowInsets.systemWindowInsetBottom
            return height == insetsBottom
        }
    }
}