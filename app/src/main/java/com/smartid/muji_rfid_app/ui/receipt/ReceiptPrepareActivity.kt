package com.smartid.muji_rfid_app.ui.receipt

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.ui.widgets.SizeList
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils

class ReceiptPrepareActivity : BaseActivity() {
    private val list: ArrayList<ReceiptCommoditiesList.State> = ArrayList()

    private lateinit var tabNew: TextView
    private lateinit var tabReplenish: TextView
    private lateinit var tabLocked: TextView

    private var popupFilter: PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_prepare)

        StatusBarUtils.immersiveStatusBar(this, findViewById<LinearLayout>(R.id.toolbar), 0f)
        initToolbar()

        initLit()
        initView()
    }

    private fun initView() {
        tabNew = findViewById(R.id.receipt_tab_new)
        tabNew.isActivated = true
        tabNew.setOnClickListener {
            onClickTab(it)
        }
        tabReplenish = findViewById(R.id.receipt_tab_replenish)
        tabReplenish.setOnClickListener {
            onClickTab(it)
        }
        tabLocked = findViewById(R.id.receipt_tab_locked)
        tabLocked.setOnClickListener {
            onClickTab(it)
        }


        var controllerFilter = findViewById<TextView>(R.id.receipt_controller_filter)
        controllerFilter.setOnClickListener {
            if (popupFilter == null) {
                popupFilterWindow()
            } else {
                popupFilter?.dismiss()
                popupFilter = null
            }
        }

        val view = findViewById<RecyclerView>(R.id.receipt_commodities_list)
        ReceiptCommoditiesList(view, list, ReceiptCommoditiesList.Status.REPLENISH)
    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货准备"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<LinearLayout>(R.id.toolbar_right)?.removeAllViews()
    }

    private fun initLit() {
        val sizes = ArrayList<SizeList.SizeState>()
        sizes.add(SizeList.SizeState("XS", "2"))
        sizes.add(SizeList.SizeState("S", "3"))
        sizes.add(SizeList.SizeState("M", "4"))
        sizes.add(SizeList.SizeState("L", "5"))
        sizes.add(SizeList.SizeState("XL", "6"))
        sizes.add(SizeList.SizeState("XXL", "7"))
        val state = ReceiptCommoditiesList.State()
        state.id = "4550344730828"
        state.name = "纯棉衬衫"
        state.image = ""
        state.areaSells = "45"
        state.storeSells = "37"
        state.isLock = false
        state.lockedDate = "09/12\n11:49"
        state.specs = ""
        state.sizes = sizes
        state.exp = "8"
        state.price = "128"
        state.isNew = false
        list.add(state)

        val state1 = ReceiptCommoditiesList.State()
        state1.id = "4550344730828"
        state1.name = "纯棉衬衫"
        state1.image = ""
        state1.areaSells = "45"
        state1.storeSells = "37"
        state1.isLock = true
        state1.lockedDate = "09/12\n11:49"
        state1.specs = ""
        state1.sizes = sizes
        state1.exp = "8"
        state1.price = "128"
        state1.isNew = false

        list.add(state1)
    }

    private fun onClickTab(view: View) {
        tabNew.isActivated = false
        tabReplenish.isActivated = false
        tabLocked.isActivated = false
        view.isActivated = true
    }

    private fun popupFilterWindow() {
        val parent = findViewById<LinearLayout>(R.id.receipt_controller)
        val view = layoutInflater.inflate(R.layout.receipt_prepare_filter, null, false)

        val parentPosition = Utils.getViewLocation(parent)
        popupFilter = PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, (resources.displayMetrics.heightPixels - parentPosition[1] - parentPosition[3]))
        popupFilter?.setBackgroundDrawable(ColorDrawable())
        popupFilter?.isOutsideTouchable = true
        popupFilter?.showAsDropDown(parent, 0, 0)
    }

    override fun onBackPressed() {
        if (popupFilter !== null) {
            popupFilter?.dismiss()
            popupFilter = null
        } else {
            super.onBackPressed()
        }
    }
}