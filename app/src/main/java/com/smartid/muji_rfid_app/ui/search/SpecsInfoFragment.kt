package com.smartid.muji_rfid_app.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils


class SpecsInfoFragment : Fragment() {
    private var list: ArrayList<SpecsInfo.State>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_specs_info, container, false)
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
        val imageView = ImageView(requireContext())
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.width = Utils.dip2px(requireContext(), 24f).toInt()
        layoutParams.height = Utils.dip2px(requireContext(), 24f).toInt()
        imageView.layoutParams = layoutParams
        imageView.setImageResource(R.drawable.ic_search_details)
        toolbarRightView.addView(imageView)

        val title = view.findViewById<TextView>(R.id.toolbar_title)
        title.text = "4550344-731161 21AW\nWOMAN SHRT"

        var recyclerView = view.findViewById<RecyclerView>(R.id.search_specs_recycle_view)
        SpecsInfo(recyclerView, list!!)
    }

    private fun initList() {
        list = ArrayList()
        list!!.add(SpecsInfo.State("IMAGE", "4550344731161"))
        list!!.add(SpecsInfo.State("货        号：", "4550344731161"))
        list!!.add(SpecsInfo.State("商品名称：", "法兰绒 中长衬衫"))
        list!!.add(SpecsInfo.State("售        价：", "￥128.8"))
        list!!.add(SpecsInfo.State("商品材质：", "羊毛70%，耗牛绒30%"))
        list!!.add(SpecsInfo.State("尺        码：", "女士S/女士M/女士L/女士XL"))
        list!!.add(SpecsInfo.State("颜        色：", "炭灰色/黑色/燕麦色/米色/红色/烟熏粉色/深咖啡色/深咖啡棕色"))
        list!!.add(SpecsInfo.State("产        地：", "柬埔寨"))
        list!!.add(SpecsInfo.State("季        节：", "冬"))
        list!!.add(SpecsInfo.State("性        别：", "Unisex"))
        list!!.add(SpecsInfo.State("品        番：", "BCJ16C1A"))
        list!!.add(SpecsInfo.State("国家标准：", "GB 31701-2015 B类"))
        list!!.add(SpecsInfo.State("实行标准：", "FZ/T 73020-2012"))
        list!!.add(SpecsInfo.State("部        门：", "4142"))
    }

}