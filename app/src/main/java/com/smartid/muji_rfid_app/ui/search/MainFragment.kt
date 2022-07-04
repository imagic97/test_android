package com.smartid.muji_rfid_app.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtils.immersiveStatusBar(requireActivity(), requireView().findViewById(R.id.toolbar), 0.0f)
        initView()
    }


    fun initView() {
        val backBtn = requireView().findViewById<Button>(R.id.toolbar_back_btn)
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val toolbarRightView = requireView().findViewById<LinearLayout>(R.id.toolbar_right)
        toolbarRightView.removeAllViews()

        val toolbarCenterView = requireView().findViewById<LinearLayout>(R.id.toolbar_center)
        toolbarCenterView.removeAllViews()

        val imageView = ImageView(requireContext())
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.width = Utils.dip2px(requireContext(), 78f).toInt()
        layoutParams.height = Utils.dip2px(requireContext(), 32f).toInt()
        imageView.layoutParams = layoutParams
        imageView.setImageResource(R.drawable.ic_muji_logo)
        toolbarCenterView.addView(imageView)

        requireView().findViewById<Button>(R.id.search_scan_btn).setOnClickListener {
            (requireActivity() as SearchActivity).replaceFragment(CustomerDetailsFragment())
        }
    }
}