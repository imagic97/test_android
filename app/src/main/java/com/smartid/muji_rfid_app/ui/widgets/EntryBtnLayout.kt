package com.smartid.muji_rfid_app.ui.widgets

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import com.smartid.muji_rfid_app.R


@SuppressLint("ClickableViewAccessibility")
class EntryBtnLayout @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    private val mPaint =
        Paint(Paint.ANTI_ALIAS_FLAG)

    /** 背景色 */
    private var mBgColor = Color.TRANSPARENT

    /** 动画背景色 */
    private var mOverColor = Color.TRANSPARENT

    private var mRadius = 0f

    private var mOverRadius = 0f

    private var from = 0f

    private var isAnimated: Boolean

    private lateinit var mOverBg: Path

    private lateinit var mBgRectF: RectF

    private var animation: ValueAnimator? = null

    private var modal: Float = 1f

    private var mAnimation = TRANSLATE


    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null) // 关闭硬件加速
        setWillNotDraw(false) // 调用此方法后，才会执行 onDraw(Canvas) 方法

        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.EntryBtnLayout)
        mBgColor = typedArray?.getColor(R.styleable.EntryBtnLayout_backgroundColor, context.getColor(R.color.transparent))!!
        mOverColor = typedArray.getColor(R.styleable.EntryBtnLayout_overColor, context.getColor(R.color.transparent))
        mRadius = typedArray.getDimension(R.styleable.EntryBtnLayout_radius, 0f)
        mOverRadius = typedArray.getDimension(R.styleable.EntryBtnLayout_overRadius, 0f)
        from = typedArray.getDimension(R.styleable.EntryBtnLayout_from, 0f)
        isAnimated = typedArray.getBoolean(R.styleable.EntryBtnLayout_isAnimated, false)
        mAnimation = typedArray.getInt(R.styleable.EntryBtnLayout_animation, TRANSLATE)

        typedArray.recycle()

        setUpShadowPaint()

        if (isAnimated) {
            initAnimation()
        }

    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                modal = 0f
                if (isAnimated) {
                    animation?.start()
                } else {
                    this.isPressed = true
                    modal = 0f
                    // 无动画下，手动更新
                    this.invalidate()
                }
            }

            MotionEvent.ACTION_UP -> {
                modal = 1f
                if (isAnimated) {
                    animation?.end()
                    animation?.resume()
                } else {
                    this.isPressed = false
                    // 更新
                    this.invalidate()
                }
                performClick()
            }
        }
        return super.onTouchEvent(ev)
    }

    private fun initAnimation() {
        animation = ValueAnimator.ofFloat(1f, 0f)
        animation?.duration = 300
        animation?.addUpdateListener {
            modal = it.animatedValue as Float
            this.invalidate()
        }

        animation?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                isPressed = true
            }

            override fun onAnimationEnd(p0: Animator?) {
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
                isPressed = true
            }
        })
        animation?.interpolator = LinearInterpolator()
    }

    private fun setUpShadowPaint() {
        mPaint.reset()
        mPaint.isAntiAlias = true
        mPaint.color = Color.TRANSPARENT
        mPaint.style = Paint.Style.STROKE
    }

    private fun initDraw() {
        mBgRectF = RectF(0f, 0f, width.toFloat(), height.toFloat())

        val overBg = Path()
        var roundRectF: RectF
        var radii: FloatArray

        if (mAnimation == SCALE) {
            roundRectF = RectF(0f, 0f, modal * width.toFloat(), modal * height.toFloat())
            radii = floatArrayOf(mRadius, mRadius, mRadius, mRadius, modal * mOverRadius, modal * mOverRadius, mRadius, mRadius)
        } else {
            // 动画变化宽度，由 modal 变化触发
            val calWidth = (-modal + 1) * (width.toFloat())
            // 动画变化圆角
            var calRadius = mOverRadius

            if (width - mOverRadius < calWidth) {
                // 开始缩减圆角
                calRadius = width - calWidth + mRadius
            }
            roundRectF = RectF(0f, 0f, calWidth, height.toFloat())
            radii = floatArrayOf(mRadius, mRadius, calRadius, calRadius, calRadius, calRadius, mRadius, mRadius)
        }

        overBg.addRoundRect(roundRectF, radii, Path.Direction.CW)
        mOverBg = overBg
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        initDraw()
        mPaint.style = Paint.Style.FILL
        mPaint.color = mBgColor
        canvas?.drawRoundRect(mBgRectF, mRadius, mRadius, mPaint)

        mPaint.color = mOverColor
        canvas?.drawPath(mOverBg, mPaint)
    }

    companion object {
        const val TRANSLATE = 0x0001
        const val SCALE = 0x0010
    }
}