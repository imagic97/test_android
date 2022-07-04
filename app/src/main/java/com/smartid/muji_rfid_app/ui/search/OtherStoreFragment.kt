package com.smartid.muji_rfid_app.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.utils.StatusBarUtils


class OtherStoreFragment : Fragment() {
    private var list: ArrayList<OtherStoreList.StoreState>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_other_store, container, false)
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtils.immersiveStatusBar(requireActivity(), requireView().findViewById(R.id.toolbar), 0.0f)

        initList()
        initView()
    }

    fun initView() {
        val view = requireView()
        val backBtn = view.findViewById<Button>(R.id.toolbar_back_btn)
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val toolbarRightView = view.findViewById<LinearLayout>(R.id.toolbar_right)
        toolbarRightView.removeAllViews()

        val title = view.findViewById<TextView>(R.id.toolbar_title)
        title.text = "4550344-731161 21AW\nWOMAN SHRT"

        val filterList = arrayListOf("XS(160/80A)", "S(165/84A)", "M(170/88A)", "XL(175/88A)")
        OtherStoreFilterList(view.findViewById(R.id.search_other_store_filter_recycle_view), filterList)
        OtherStoreList(view.findViewById(R.id.search_other_store_recycle_view), list!!, this)
    }

    private fun initList() {
        list = ArrayList()
        val specsList = arrayListOf("XS", "S", "M", "L", "XL", "XXL")
        list!!.add(OtherStoreList.StoreState("500096", "伤害悠迈生活广场", "13800138000", specsList))
        list!!.add(OtherStoreList.StoreState("500096", "伤害悠迈生活广场", "13800138000", specsList))
        list!!.add(OtherStoreList.StoreState("500096", "伤害悠迈生活广场", "13800138000", specsList))
        list!!.add(OtherStoreList.StoreState("500096", "伤害悠迈生活广场", "13800138000", specsList))
        list!!.add(OtherStoreList.StoreState("500096", "伤害悠迈生活广场", "13800138000", specsList))
        list!!.add(OtherStoreList.StoreState("500096", "伤害悠迈生活广场", "13800138000", specsList))
    }


}