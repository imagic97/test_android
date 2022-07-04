package com.smartid.muji_rfid_app.utils

import android.content.Context
import android.view.View
import java.util.*

class Utils {
    companion object {
        fun dip2px(context: Context, dpValue: Float): Float {
            val dm = context.resources.displayMetrics
            val scale = dm.density
            return dpValue * scale + 0.5f
        }

        fun getViewLocation(view: View): IntArray {
            val loc = IntArray(4)
            val location = IntArray(2)
            view.getLocationOnScreen(location)
            loc[0] = location[0]
            loc[1] = location[1]
            val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view.measure(w, h)

            loc[2] = view.measuredWidth
            loc[3] = view.measuredHeight
            // PositionX, PositionY, width, height
            return loc
        }

        fun newRandomNumber(): String {
            return (Random().nextInt(50) + 50).toString() + ""
        }
    }
}