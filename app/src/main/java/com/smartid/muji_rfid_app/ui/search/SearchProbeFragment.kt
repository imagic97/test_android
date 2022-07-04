package com.smartid.muji_rfid_app.ui.search

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.widgets.tableLayout.TableAdapter
import com.smartid.muji_rfid_app.ui.widgets.tableLayout.TableLayout
import com.smartid.muji_rfid_app.ui.widgets.tableLayout.Util
import com.smartid.muji_rfid_app.utils.StatusBarUtils
import com.smartid.muji_rfid_app.utils.Utils


class SearchProbeFragment : Fragment() {

    private var contentList: java.util.ArrayList<java.util.ArrayList<String>>? = null
    private var tableLayout: TableLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_probe, container, false)
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtils.immersiveStatusBar(requireActivity(), requireView().findViewById(R.id.toolbar), 0.0f)

        initView()

        initContent()
        tableLayout = requireView().findViewById(R.id.main_table) as TableLayout
        firstRowAsTitle(tableLayout!!)
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

        view.findViewById<Button>(R.id.tab_read).isActivated = false
    }

    private fun firstRowAsTitle(view: TableLayout) {
        view.adapter = object : TableAdapter {
            override fun getColumnCount(): Int {
                return contentList!![0].size
            }

            override fun getColumnContent(position: Int): Array<String?> {
                val rowCount = contentList!!.size
                val contents = arrayOfNulls<String>(rowCount)
                try {
                    for (i in 0 until rowCount) {
                        contents[i] = contentList!![i][position]
                    }
                } catch (e: NoSuchFieldException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
                return contents
            }

            override fun onCreateCellView(view: TextView, x: Int, y: Int) {
                view.setTextColor(Color.parseColor("#000000"))
                when (x) {
                    0 -> {
                        view.setTextColor(Color.parseColor("#000000"))
                    }
                    else -> {
                        when (y) {
                            0 -> {
                                view.setTextColor(createStateListTextColor("#92959B", "#FFFFFF"))
                            }
                            else -> {
                                view.setTextColor(createStateListTextColor("#000000", "#FFFFFF"))
                            }
                        }
                    }
                }
            }

            override fun setCellViewWidth(x: Int): Int {
                return Util.dip2px(view.resources, 112f).toInt()
            }

            override fun onClick(x: Int, y: Int) {
            }
        }
    }

    private fun initContent() {
        contentList = java.util.ArrayList()
        contentList!!.add(arrayListOf("尺寸", "卖场", "仓库"))
        contentList!!.add(arrayListOf("S", Utils.newRandomNumber(), Utils.newRandomNumber()))
        contentList!!.add(arrayListOf("M", Utils.newRandomNumber(), Utils.newRandomNumber()))
        contentList!!.add(arrayListOf("L", Utils.newRandomNumber(), Utils.newRandomNumber()))
        contentList!!.add(arrayListOf("XL", Utils.newRandomNumber(), Utils.newRandomNumber()))
        contentList!!.add(arrayListOf("XXL", Utils.newRandomNumber(), Utils.newRandomNumber()))
    }

    private fun createStateListTextColor(defaultColor: String, activeColor: String): ColorStateList {
        val default = Color.parseColor(defaultColor)
        val active = Color.parseColor(activeColor)

        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_activated),
                intArrayOf()
            ),
            intArrayOf(active, default)
        )
    }

}