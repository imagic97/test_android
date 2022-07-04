package com.smartid.muji_rfid_app.ui.search

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.widgets.SizeList
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils


class ReportFragment : Fragment() {

    var list = ArrayList<ReportList.State>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stock_report, container, false)
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtils.immersiveStatusBar(requireActivity(), requireView().findViewById(R.id.toolbar), 0.0f)

        initView()
        initList()
        ReportList(requireView().findViewById(R.id.search_report_list), list)
    }

    fun initView() {
        val view = requireView()
        val backBtn = view.findViewById<Button>(R.id.toolbar_back_btn)
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val title = view.findViewById<TextView>(R.id.toolbar_title)
        title.text = "库存报告"

        val toolbarRightView = view.findViewById<LinearLayout>(R.id.toolbar_right)
        toolbarRightView.removeAllViews()
        val imageView = ImageView(requireContext())
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.width = Utils.dip2px(requireContext(), 24f).toInt()
        layoutParams.height = Utils.dip2px(requireContext(), 24f).toInt()
        imageView.layoutParams = layoutParams
        imageView.setImageResource(R.drawable.ic_filter)

        imageView.setOnClickListener {
            showPopupWindow()
        }

        toolbarRightView.addView(imageView)
    }

    private fun showPopupWindow() {
        val dialog = Dialog(requireContext(), R.style.DialogTheme_BottomSheet)
        val view = View.inflate(requireContext(), R.layout.activity_search_options_popup, null)
        dialog.setContentView(view)
        val window = dialog.window!!
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.bottomSheet_Animation)
        dialog.show()
    }


    fun initList() {
        val sizesList = ArrayList<SizeList.SizeState>()
        sizesList.add(SizeList.SizeState("XS", "-"))
        sizesList.add(SizeList.SizeState("S", "0/4"))
        sizesList.add(SizeList.SizeState("M", "0/4"))
        sizesList.add(SizeList.SizeState("L", "-"))
        sizesList.add(SizeList.SizeState("XL", "-"))

        val commoditiesList = ArrayList<ReportList.State>()
        val commodity1 = ReportList.State("男装", "衬衫", "null", "4550344730828", "纯棉衬衫1", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity2 = ReportList.State("男装", "衬衫", "null", "4550344730828", "纯棉衬衫2", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity3 = ReportList.State("男装", "衬衫", "null", "4550344730828", "纯棉衬衫3", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity4 = ReportList.State("男装", "衬衫", "null", "4550344730828", "纯棉衬衫4", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity5 = ReportList.State("女装", "衬衫", "null", "4550344730828", "纯棉衬衫5", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity6 = ReportList.State("女装", "衬衫", "null", "4550344730828", "纯棉衬衫6", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity7 = ReportList.State("女装", "衬衫", "null", "4550344730828", "纯棉衬衫7", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity8 = ReportList.State("女装", "衬衫", "null", "4550344730828", "纯棉衬衫8", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity9 = ReportList.State("食品", "衬衫", "null", "4550344730828", "纯棉衬衫9", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity10 = ReportList.State("食品", "衬衫", "null", "4550344730828", "纯棉衬衫10", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity11 = ReportList.State("食品", "衬衫", "null", "4550344730828", "纯棉衬衫11", "128", "藏青色", "30", "9", "256", false, sizesList)
        val commodity12 = ReportList.State("食品", "衬衫", "null", "4550344730828", "纯棉衬衫12", "128", "藏青色", "30", "9", "256", false, sizesList)
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


}