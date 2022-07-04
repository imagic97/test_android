package com.smartid.muji_rfid_app.ui.widgets

import android.content.Context
import android.content.Intent
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.smartid.muji_rfid_app.R

class DrawerLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
    private var closeBtn: Button? = null

    private val list = ArrayList<DrawerItem>()

    init {
        LayoutInflater.from(context).inflate(R.layout.main_drawer, this, true)
        closeBtn = findViewById(R.id.drawer_close_btn)
        val listView: ListView = findViewById(R.id.main_drawer_list)

        initList()

        val adapter = DrawerAdapter(context, R.layout.main_drawer_item, list)
        listView.adapter = adapter

        closeBtn?.setOnClickListener {
            this.visibility = View.GONE
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val item = list[position]
//            adapter.notifyDataSetChanged()
            when (item.title) {
                "店内移动" -> {
                    val intent = Intent("android.intent.action.moveInShop")
                        .addCategory("android.intent.category.moveInShop")
                    context.startActivity(intent)
                }
            }

            Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initList() {
        list.add(DrawerItem("店内移动", R.drawable.ic_move_in_shop, R.drawable.ic_move_in_shop_2))
        list.add(DrawerItem("商品补充", R.drawable.ic_commodities_replenish, R.drawable.ic_commodities_replenish_2))
        list.add(DrawerItem("商品查询", R.drawable.ic_commodities_search, R.drawable.ic_commodities_search_2))
        list.add(DrawerItem("门店收货", R.drawable.ic_shop_receipt, R.drawable.ic_shop_receipt_2))
        list.add(DrawerItem("EC取货", R.drawable.ic_ec_pickup, R.drawable.ic_ec_pickup_2))
        list.add(DrawerItem("转出", R.drawable.ic_transfer_out, R.drawable.ic_transfer_out_2))
        list.add(DrawerItem("RFID库存修正", R.drawable.ic_stock_fix, R.drawable.ic_stock_fix_2))
        list.add(DrawerItem("RFID库存读取", R.drawable.ic_stock_red, R.drawable.ic_stock_red_2))
        list.add(DrawerItem("RFID信息修正", R.drawable.ic_info_fix, R.drawable.ic_info_fix_2))
        list.add(DrawerItem("报告", R.drawable.ic_report, R.drawable.ic_report_2))
        list.add(DrawerItem("设置", R.drawable.ic_settings, R.drawable.ic_settings_2))
        list.add(DrawerItem("其它功能", R.drawable.ic_more, R.drawable.ic_more_2))
    }

    inner class DrawerItem(val title: String, val icon: Int, val iconHover: Int)


    inner class DrawerAdapter(
        activity: Context,
        private val resourceId: Int,
        data: List<DrawerItem>,
    ) :
        ArrayAdapter<DrawerItem>(activity, resourceId, data) {

        inner class ViewHolder(val text: TextView, val icon: ImageView)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View
            val viewHolder: ViewHolder
            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(resourceId, parent, false)
                val icon: ImageView = view.findViewById(R.id.drawer_item_icon)
                val text: TextView = view.findViewById(R.id.drawer_item_text)
                viewHolder = ViewHolder(text, icon)
                view.tag = viewHolder

            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            val item = getItem(position)

            if (item != null) {
                viewHolder.icon.setImageDrawable(createStateListDrawable(item.icon, item.iconHover))
                viewHolder.text.text = item.title
            }

            return view
        }

        private fun createStateListDrawable(idNormal: Int, idPressed: Int): StateListDrawable {
            val bg = StateListDrawable()
            val normal = context.resources.getDrawable(idNormal, null)
            val pressed = context.resources.getDrawable(idPressed, null)

            bg.addState(intArrayOf(android.R.attr.state_pressed), pressed)
            bg.addState(intArrayOf(), normal)

            return bg
        }
    }
}