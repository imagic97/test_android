package com.smartid.muji_rfid_app.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import com.smartid.muji_rfid_app.utils.StatusBarUtils

class LoginActivity : BaseActivity() {

    private val list = ArrayList<Shop>()

    private lateinit var listView: ListView
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        StatusBarUtils.immersiveStatusBar(this, null, 0f)

        initList()
        initEvent()
    }

    private fun initEvent() {
        findViewById<EditText>(R.id.common_input).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO
                updateList()
            }

            override fun afterTextChanged(p0: Editable?) {
                // TODO
            }
        })

        listView = findViewById(R.id.login_shop_search_list)
        shopListAdapter = ShopListAdapter(this, R.layout.activity_login_select_item, list)
        listView.adapter = shopListAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val item = list[position]
        }
    }

    private fun initList() {
        list.add(Shop("50017", "上海美罗城"))
        list.add(Shop("50018", "上海aa城"))
        list.add(Shop("50019", "上海bb城"))
    }

    fun updateList() {
        list.removeAll(list)
        list.add(Shop((0..10).random().toString(), "上海美罗城"))

        shopListAdapter.notifyDataSetChanged()
    }
}