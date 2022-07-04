package com.smartid.muji_rfid_app.ui.receipt

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.ui.common.AppProgress
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils

class ReceiptPackageActivity : BaseActivity() {
    private val list: ArrayList<ReceiptPackageList.State> = ArrayList()
    private var popupWindow: PopupWindow? = null

    private lateinit var popupWindowBtn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_package)

        StatusBarUtils.immersiveStatusBar(this, findViewById<LinearLayout>(R.id.toolbar), 0f)
        initToolbar()

        initLit()
        initView()
    }

    private fun initView() {
        val view = findViewById<RecyclerView>(R.id.receipt_package_list)
        ReceiptPackageList(view, list)

        popupWindowBtn = findViewById(R.id.receipt_popup_btn)
        popupWindowBtn.setOnClickListener {
            showPopupWindow()
        }

        val popupWindowProcess = findViewById<Button>(R.id.receipt_scan_btn)
        popupWindowProcess.setOnClickListener {
            AppProgress(this).show()
        }

        val actionNextStep = findViewById<ImageButton>(R.id.receipt_next_btn)
        actionNextStep.setOnClickListener {
            val intent = Intent(this, ReceiptCommodityConfirmActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<LinearLayout>(R.id.toolbar_right)?.removeAllViews()
    }

    private fun initLit() {
        // B: RFID商品
        // M：非RFID商品
        // 箱子自动按照RFID箱在前，
        // 非RFID箱在后顺序
        list.add(ReceiptPackageList.State("50061211020389173", "M", false, isReceipt = null))
        list.add(ReceiptPackageList.State("50061211020389173", "M", true, isReceipt = null))
        list.add(ReceiptPackageList.State("50061211020389173", "M", false, isReceipt = null))
        list.add(ReceiptPackageList.State("50061211020389173", "M", false, isReceipt = true))
        list.add(ReceiptPackageList.State("50061211020389173", "M", false, isReceipt = false))
        list.add(ReceiptPackageList.State("50061211020389173", "M", false, isReceipt = null))
        list.add(ReceiptPackageList.State("50061211020389173", "M", false, isReceipt = null))
    }

    private fun showPopupWindow() {
        if (popupWindow == null) {
            val view = layoutInflater.inflate(R.layout.receipt_popup, null, false)

            popupWindow = PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            popupWindow?.setBackgroundDrawable(ColorDrawable())
            popupWindow?.isOutsideTouchable = true
            // 设置window焦点，isShowing 才有效
            popupWindow?.isFocusable = true
            popupWindowBtn.isActivated = false
            popupWindow?.setOnDismissListener {
                popupWindowBtn.isActivated = false
            }
        }
        if (popupWindow != null && popupWindow?.isShowing == false) {
            val parentPosition = Utils.dip2px(popupWindowBtn.context, 43f)
            val navigationHeight = StatusBarUtils.getSystemBarHeight(popupWindowBtn.context, StatusBarUtils.NAVIGARTION_BAR_HEIGHT)
            popupWindowBtn.isActivated = true
            popupWindow?.showAtLocation(popupWindowBtn, Gravity.BOTTOM, 0, (parentPosition + navigationHeight).toInt())
            popupWindow?.isFocusable = true
        }
    }

    override fun onBackPressed() {
        if (popupWindow != null && popupWindow?.isShowing == true) {
            popupWindow?.dismiss()
        } else {
            super.onBackPressed()
        }
    }
}