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
import com.smartid.muji_rfid_app.ui.widgets.tableLayout.TableLayout
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils


class CustomerDetailsFragment : Fragment() {
    private var contentList: ArrayList<ArrayList<String>>? = null
    private var tableLayout: TableLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_details_customer, container, false)
    }


    override fun onStart() {
        super.onStart()
        StatusBarUtils.immersiveStatusBar(requireActivity(), requireView().findViewById(R.id.toolbar), 0.0f)
        initView()
        initEvent()

        initContent()
        tableLayout = requireView().findViewById(R.id.main_table) as TableLayout
        SearchTable(tableLayout!!, contentList!!)
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

        imageView.setOnClickListener {
            (activity as SearchActivity).replaceFragment(SpecsInfoFragment())
        }

        val title = view.findViewById<TextView>(R.id.toolbar_title)
        title.text = "4550344-731161 21AW\nWOMAN SHRT"

        view.findViewById<Button>(R.id.search_other_store_btn).setOnClickListener {
            (activity as SearchActivity).replaceFragment(OtherStoreFragment())
        }

        view.findViewById<Button>(R.id.search_specs_details_btn).setOnClickListener {
            (activity as SearchActivity).replaceFragment(SpecsInfoFragment())
        }

        view.findViewById<Button>(R.id.search_probe_btn).setOnClickListener {
            (activity as SearchActivity).replaceFragment(SearchProbeFragment())
        }
    }

    private fun initEvent() {
        val view = requireView()
        val specsSwiper = view.findViewById<RecyclerView>(R.id.search_specs_swiper)
        val list = ArrayList<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")
        list.add("6")
        list.add("7")
        list.add("8")
        list.add("9")
        list.add("70")
        val swiper = SpecsSwiper(requireContext(), specsSwiper, list)

        view.findViewById<ImageView>(R.id.search_specs_swiper_next).setOnClickListener {
            swiper.next()
        }
        view.findViewById<ImageView>(R.id.search_specs_swiper_prev).setOnClickListener {
            swiper.prev()
        }
    }

    private fun initContent() {
        contentList = ArrayList()
        contentList!!.add(arrayListOf("", "XS", "S", "M", "L", "XL", "XXL"))
        contentList!!.add(arrayListOf("卖场实际", Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber()))
        contentList!!.add(arrayListOf("后        仓", Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber()))
        contentList!!.add(arrayListOf("大        仓", Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber()))
        contentList!!.add(arrayListOf("在        途", Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber(), Utils.newRandomNumber()))
    }


}