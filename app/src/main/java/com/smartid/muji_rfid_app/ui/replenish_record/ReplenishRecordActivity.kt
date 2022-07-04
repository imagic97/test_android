package com.smartid.muji_rfid_app.ui.replenish_record

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils

class ReplenishRecordActivity : BaseActivity() {

    val list = ArrayList<RecordState>()

    lateinit var listView: ListView
    lateinit var listAdapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replenish_record)

        StatusBarUtils.immersiveStatusBar(this, findViewById(R.id.toolbar), 0.0f)

        initList()
        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.toolbar_back_btn).setOnClickListener {
            finish()
        }
        findViewById<LinearLayout>(R.id.toolbar_right).removeAllViews()
        val toolbarCenterView = findViewById<LinearLayout>(R.id.toolbar_center)
        toolbarCenterView.removeAllViews()

        val imageView = ImageView(this)
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.width = Utils.dip2px(this, 78f).toInt()
        layoutParams.height = Utils.dip2px(this, 32f).toInt()
        imageView.layoutParams = layoutParams
        imageView.setImageResource(R.drawable.ic_muji_logo)
        toolbarCenterView.addView(imageView)


        listView = findViewById(R.id.table_list_view)
        listAdapter = RecordAdapter(this, R.layout.activity_replenish_record_item, list)
        listView.adapter = listAdapter
    }

    private fun initList() {
        list.add(RecordState(RecordState.READY, "08:00-09:00", "35", "32"))
        list.add(RecordState(RecordState.FINISH, "08:00-09:00", "35", "32"))
        list.add(RecordState(RecordState.PART, "08:00-09:00", "35", "32"))
    }
}