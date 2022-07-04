package com.smartid.muji_rfid_app.ui.replenish_options

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils

class ReplenishOptionsActivity : BaseActivity() {

    private val list1 = ArrayList<SelectionState>()
    private val list2 = ArrayList<SelectionState>()
    private val list3 = ArrayList<SelectionState>()
    private val list4 = ArrayList<SelectionState>()

    private var status = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replenish_options)

        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.toolbar), 0.0f)
        initView()

        initList1()
        initList2()
        initList3()
        initList4()

        replaceFragment(OptionsFragment(list1))
    }

    private fun initView() {
        findViewById<Button>(R.id.toolbar_back_btn).setOnClickListener {
            onBackPressed()
        }
        val submit = findViewById<TextView>(R.id.toolbar_menu)
        submit.text = "OK"
        submit.setOnClickListener {
            when (status) {
                0 -> {
                    status = 1
                    replaceFragment(OptionsFragment(list2))
                }
                1 -> {
                    status = 2
                    replaceFragment(OptionsFragment(list3))
                }
                2 -> {
                    status = 3
                    replaceFragment(OptionsFragment(list4))
                }
            }
        }

        val toolbarCenterView = findViewById<LinearLayout>(R.id.toolbar_center)
        toolbarCenterView.removeAllViews()

        val imageView = ImageView(this)
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.width = Utils.dip2px(this, 78f).toInt()
        layoutParams.height = Utils.dip2px(this, 32f).toInt()
        imageView.layoutParams = layoutParams
        imageView.setImageResource(R.drawable.ic_muji_logo)
        toolbarCenterView.addView(imageView)
    }

    private fun initList1() {
        var list = ArrayList<OptionState>()
        list.add(OptionState("冬季·2020", false))
        list.add(OptionState("夏季·2020", false))
        list1.add(SelectionState("【请选择一项或多项活动】", list))

        list = ArrayList()
        list.add(OptionState("自动", false))
        list.add(OptionState("仓库", false))
        list.add(OptionState("DPET", false))
        list.add(OptionState("LINE", false))
        list1.add(SelectionState("【选择报告类型】", list))

        list = ArrayList()
        list.add(OptionState("子系列", false))
        list1.add(SelectionState("【报告顺序】", list))
    }

    private fun initList2() {
        val list = ArrayList<OptionState>()
        list.add(OptionState("1F 南仓库", false))
        list.add(OptionState("2F 北仓库", false))
        list.add(OptionState("2F 仓库", false))
        list2.add(SelectionState("【选择仓库】", list))
    }

    private fun initList3() {
        val list = ArrayList<OptionState>()
        list.add(OptionState("101：男装", false))
        list.add(OptionState("102：女装", false))
        list.add(OptionState("103：儿童", false))
        list3.add(SelectionState("【选择DPET】", list))
    }

    private fun initList4() {
        val list = ArrayList<OptionState>()
        list.add(OptionState("001：衬衫", false))
        list.add(OptionState("002：短袖", false))
        list.add(OptionState("003：儿童", false))
        list4.add(SelectionState("【选择LINE】", list))
    }
}