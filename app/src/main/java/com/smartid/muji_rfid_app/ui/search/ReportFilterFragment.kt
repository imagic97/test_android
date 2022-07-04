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


class ReportFilterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stock_report_filter, container, false)
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtils.immersiveStatusBar(requireActivity(), requireView().findViewById(R.id.toolbar), 0.0f)

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
        title.text = "库存报告"

        view.findViewById<Button>(R.id.submit_btn).setOnClickListener {
            (requireActivity() as SearchActivity).replaceFragment(ReportFragment())
        }

    }

}