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
import com.smartid.muji_rfid_app.ui.widgets.tableLayout.TableLayout
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import java.util.*

class OtherStoreDetailsFragment : Fragment() {
    private var contentList: ArrayList<ArrayList<String>>? = null
    private var tableLayout: TableLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_other_store_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtils.immersiveStatusBar(requireActivity(), requireView().findViewById(R.id.toolbar), 0.0f)

        initView()
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

        val title = view.findViewById<TextView>(R.id.toolbar_title)
        title.text = "4550344-731161 21AW\nWOMAN SHRT"
    }

    private fun initContent() {
        contentList = ArrayList()
        contentList!!.add(arrayListOf("", "XS", "S", "M", "L", "XL", "XXL"))
        contentList!!.add(arrayListOf("库存", newRandomNumber(), newRandomNumber(), newRandomNumber(), newRandomNumber(), newRandomNumber(), newRandomNumber()))
    }

    private fun newRandomNumber(): String {
        return (Random().nextInt(50) + 50).toString() + ""
    }


}