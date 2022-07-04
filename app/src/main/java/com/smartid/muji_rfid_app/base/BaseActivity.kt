package com.smartid.muji_rfid_app.base

import android.content.Context
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R

open class BaseActivity : AppCompatActivity() {
    private var _popupWindow: PopupWindow? = null

    /**
     * 记录需要拦截事件的 popup 弹窗，内容区域外点击阻止穿透
     */
    fun setCurrentPopupWindow(popupWindow: PopupWindow) {
        this._popupWindow = popupWindow
    }

    override fun finish() {
        if (currentFocus != null) {
            hideSoftInput(currentFocus!!.windowToken)
        }
        super.finish()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // 拦截 popupWindow 弹窗外部点击事件
        if (_popupWindow != null && _popupWindow?.isShowing == true) {
            return false
        }

        if (ev != null && ev.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view != null && isHideInput(view, ev)) {
                view.clearFocus()
                hideSoftInput(view.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun isHideInput(view: View?, ev: MotionEvent): Boolean {
        if (view != null && view is EditText) {
            val intArray = intArrayOf(0, 0)
            view.getLocationInWindow(intArray)

            val left = intArray[0]
            val top = intArray[1]
            val right = left + view.width
            val bottom = top + view.height

            return !(ev.x > left && ev.x < right && ev.y > top && ev.y < bottom)
        }
        return false
    }

    private fun hideSoftInput(token: IBinder) {
        val manager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (manager.isActive) {
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    fun replaceFragment(fragment: Fragment, addToNavigation: Boolean = true) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_content, fragment)
        if (addToNavigation) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            this.finish()
        }
    }
}