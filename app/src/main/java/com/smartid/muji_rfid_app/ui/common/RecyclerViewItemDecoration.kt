package com.smartid.muji_rfid_app.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration(var orientation: Int, var width: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            outRect.left = width
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = 0
            }
        } else {
            outRect.top = width
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0
            }
        }
    }
}