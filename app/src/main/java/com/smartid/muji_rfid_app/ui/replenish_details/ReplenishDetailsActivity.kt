package com.smartid.muji_rfid_app.ui.replenish_details

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.ui.common.dialog.OnButtonClickListener
import com.smartid.muji_rfid_app.ui.replenish_details.StickyItemDecoration.GroupBean
import com.smartid.muji_rfid_app.ui.widgets.SizeList
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils


class ReplenishDetailsActivity : BaseActivity() {
    var list = ArrayList<CommodityState>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replenish_details)

        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.toolbar), 0f)
        initToolbar()
        initList()
        initView()
    }

    fun initView() {
        val listView = findViewById<RecyclerView>(R.id.activity_replenish_details_list)
        val adapter = CommodityAdapter(list)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        listView.layoutManager = layoutManager
        listView.addItemDecoration(StickyItemDecoration(listView.context, createGroupBeans(list)))
        listView.adapter = adapter
    }

    fun initToolbar() {
        findViewById<Button>(R.id.toolbar_back_btn).setOnClickListener {
            finish()
        }
        var toolbarRightView = findViewById<LinearLayout>(R.id.toolbar_right)
        toolbarRightView.removeAllViews()
        val rightImageView = ImageView(this)
        val rightLayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        rightLayoutParams.width = Utils.dip2px(this, 18f).toInt()
        rightLayoutParams.height = Utils.dip2px(this, 18f).toInt()
        rightImageView.layoutParams = rightLayoutParams
        rightImageView.setImageResource(R.drawable.ic_close_w)
        toolbarRightView.addView(rightImageView)

        rightImageView.setOnClickListener {
            val dialog = CheckPermitDialog(this)
            dialog.onButtonClickListener = object : OnButtonClickListener {
                override fun onPositiveClick() {
                }

                override fun onNegativeClick() {
                }
            }
            dialog.show()
        }

        val toolbarCenterView = findViewById<LinearLayout>(R.id.toolbar_center)
        toolbarCenterView.removeAllViews()
        val imageView = ImageView(this)
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.width = Utils.dip2px(this, 36f).toInt()
        layoutParams.height = Utils.dip2px(this, 36f).toInt()
        imageView.layoutParams = layoutParams
        imageView.setImageResource(R.drawable.ic_toolbar_scan)
        toolbarCenterView.addView(imageView)
    }

    fun initList() {
        val sizesList = ArrayList<SizeList.SizeState>()
        sizesList.add(SizeList.SizeState("XS", "-"))
        sizesList.add(SizeList.SizeState("S", "0/4"))
        sizesList.add(SizeList.SizeState("M", "0/4"))
        sizesList.add(SizeList.SizeState("L", "-"))
        sizesList.add(SizeList.SizeState("XL", "-"))

        val commoditiesList = ArrayList<CommodityState>()
        val commodity1 = CommodityState("二楼仓库", "20", "Aw", "2021", "男装1", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity2 = CommodityState("二楼仓库", "20", "Aw", "2021", "男装1", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity3 = CommodityState("二楼仓库", "20", "Aw", "2021", "男装1", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity4 = CommodityState("二楼仓库", "20", "Aw", "2021", "男装1", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity5 = CommodityState("三楼楼仓库", "20", "Aw1", "2021", "男装2", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity6 = CommodityState("三楼楼仓库", "20", "Aw1", "2021", "男装2", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity7 = CommodityState("三楼楼仓库", "20", "Aw1", "2021", "男装2", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity8 = CommodityState("三楼楼仓库", "20", "Aw1", "2021", "男装2", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity9 = CommodityState("四楼仓库", "20", "Aw2", "2021", "男装3", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity10 = CommodityState("四楼仓库", "20", "Aw2", "2021", "男装3", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity11 = CommodityState("四楼仓库", "20", "Aw2", "2021", "男装3", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        val commodity12 = CommodityState("四楼仓库", "20", "Aw2", "2021", "男装3", "衬衫", "image", "4550344", "730828", "128", "124", "藏青色", true, false, false, sizesList)
        commoditiesList.add(commodity1)
        commoditiesList.add(commodity2)
        commoditiesList.add(commodity3)
        commoditiesList.add(commodity4)
        commoditiesList.add(commodity5)
        commoditiesList.add(commodity6)
        commoditiesList.add(commodity7)
        commoditiesList.add(commodity8)
        commoditiesList.add(commodity9)
        commoditiesList.add(commodity10)
        commoditiesList.add(commodity11)
        commoditiesList.add(commodity12)

        list = commoditiesList

    }

    private fun createGroupBeans(list: ArrayList<CommodityState>): ArrayList<GroupBean?> {
        val groupBeans: ArrayList<GroupBean?> = ArrayList()
        for (data in list) {
            val i: Int = list.indexOf(data)
            val groupId = i / 4
            val groupPosition = i % 4
            var groupBean: GroupBean? = null

            if (groupPosition == 0) {
                groupBean = GroupBean(groupId, groupPosition, true, false)
            }
            if (groupPosition == 3) {
                groupBean = GroupBean(groupId, groupPosition, false, true)
            }
            groupBeans.add(groupBean)
        }
        return groupBeans
    }

}