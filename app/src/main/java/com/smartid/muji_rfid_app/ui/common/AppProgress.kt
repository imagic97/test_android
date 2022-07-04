package com.smartid.muji_rfid_app.ui.common

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.Utils
import java.lang.StrictMath.max
import java.lang.StrictMath.min

class AppProgress(val activity: BaseActivity, private val max: Float = 100f, private val min: Float = 0f, private var value: Float = 0f) : PopupWindow() {

    private val view = LayoutInflater.from(activity).inflate(R.layout.receipt_popup_progress, null, false)

    private val processText: TextView = view.findViewById(R.id.app_progress_value)
    private val graphics: ProgressBar = view.findViewById(R.id.app_progress_graphics)
    private val contentText: TextView = view.findViewById(R.id.app_progress_content)
    private val cancelBtn: Button = view.findViewById(R.id.app_progress_cancel)

    private lateinit var animator: ValueAnimator

    init {
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        this.setBackgroundDrawable(ColorDrawable())
        // 内容区可触发事件
        this.isTouchable = true
        // 非内容区不可触发事件
        this.isOutsideTouchable = false

        // 精度扩大 100
        graphics.max = this.max.toInt() * 100
        graphics.min = this.min.toInt() * 100

        // 初始进度
        graphics.setProgress(value.toInt(), true)

        contentView = view

    }

    fun show() {
        this.showAtLocation(view, Gravity.CENTER, 0, 0)
        activity.setCurrentPopupWindow(this)
        cancelBtn.setOnClickListener {
            this.dismiss()
        }
    }

    /**
     * 计算百分比值，**.**
     */
    private fun calculatePercentage(value: Float): Float {
        val length = max - min
        val percentage = value / length
        return percentage * 100
    }

    private fun getLength(): Float {
        return graphics.width - Utils.dip2px(activity, 36f)
    }

    /**
     * 更新进度动画
     */
    @SuppressLint("Recycle")
    private fun createAnimation(process: Float): ValueAnimator {
        val animation = ValueAnimator.ofFloat(value, process)
        animation.interpolator = LinearInterpolator()
        animation.duration = 1000
        animation.addUpdateListener {
            val per = calculatePercentage(animation.animatedValue as Float)
            graphics.incrementProgressBy(((animation.animatedValue as Float - value + 0.005) * 100).toInt())
            processText.text = "${per.toInt()}%"
            processText.translationX = getLength() * (per / 100)
            value = animation.animatedValue as Float
        }
        return animation
    }

    /**
     * 更新进度条进度
     */
    fun updateProcess(process: Float) {
        var value = min(process, max)
        value = max(value, min)
        this.animator = createAnimation(value)
        this.animator.start()
    }
}