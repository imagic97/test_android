package com.smartid.muji_rfid_app.ui.replenish_details

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.utils.Utils


class StickyItemDecoration(context: Context, var groupBeans: ArrayList<GroupBean?>) : RecyclerView.ItemDecoration() {
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mPaintText = Paint(Paint.ANTI_ALIAS_FLAG)

    private val height = Utils.dip2px(context, 29f).toInt()
    private val padding: Float = Utils.dip2px(context, 12f)

    init {
        mPaintText.textSize = Utils.dip2px(context, 12f)
        mPaintText.color = Color.WHITE
        mPaint.color = Color.BLACK
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            val bean = groupBeans[position]
            val states = (parent.adapter as CommodityAdapter).commodities
            val commodity = states[position]
            val headerText = "${commodity.warehouseName}  ${commodity.categoryName} 0/20"
            val footerText = "合计  0/32"
            if (bean?.isFirst == true) {
                val left = parent.left
                val top = child.top - height
                val right = parent.right
                val bottom = child.top
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)

                val textY = top.toFloat() + (height + mPaintText.textSize) / 2
                c.drawText(headerText, left.toFloat() + padding, textY, mPaintText)
                c.drawText(footerText, right.toFloat() - padding - mPaintText.measureText(footerText), textY, mPaintText)
            }

            val layoutManager = parent.layoutManager as LinearLayoutManager?
            val firstVisibleItemPosition = layoutManager!!.findFirstVisibleItemPosition()
            if (position == firstVisibleItemPosition) {
                var left = 0
                var top = 0
                var right = 0
                var bottom = 0
                if (bean?.isLast == true) {
                    if (child.bottom < height) {
                        left = parent.left
                        top = 0 - (height - child.bottom)
                        right = parent.width
                        bottom = height - (height - child.bottom)
                    } else {
                        left = parent.left
                        top = 0
                        right = parent.width
                        bottom = height
                    }
                } else {
                    left = parent.left
                    top = 0
                    right = parent.width
                    bottom = height
                }
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)

                val textY = top + (height + mPaintText.textSize) / 2
                c.drawText(headerText, left + padding, textY, mPaintText)
                c.drawText(footerText, right - padding - mPaintText.measureText(footerText), textY, mPaintText)
            }
        }
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (groupBeans[position]?.isFirst == true) {
            outRect.top = height
        }
    }

    class GroupBean(val groupId: Int, val groupPosition: Int, val isFirst: Boolean, val isLast: Boolean)
}